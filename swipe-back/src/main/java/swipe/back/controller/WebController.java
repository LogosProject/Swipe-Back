package swipe.back.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import swipe.back.dao.ProblemRepository;
import swipe.back.dao.SolutionRepository;
import swipe.back.dao.UserRepository;
import swipe.back.dao.ValueRepository;
import swipe.back.dao.VersusRepository;
import swipe.back.domain.Comment;
import swipe.back.domain.Problem;
import swipe.back.domain.Solution;
import swipe.back.domain.SolutionScore;
import swipe.back.domain.User;
import swipe.back.domain.Value;
import swipe.back.domain.ValueScore;
import swipe.back.domain.ValueSolutionScore;
import swipe.back.domain.Versus;
import swipe.back.domain.VersusResponse;
import swipe.back.services.ICommentService;
import swipe.back.services.IProblemService;
import swipe.back.services.ISolutionScoreService;
import swipe.back.services.ISolutionService;
import swipe.back.services.IUserService;
import swipe.back.services.IValueScoreService;
import swipe.back.services.IValueService;
import swipe.back.services.IValueSolutionScoreService;
import swipe.back.services.IVersusResponseService;
import swipe.back.services.IVersusService;

//@EnableAutoConfiguration
@Controller
public class WebController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ProblemRepository problemRepository;
	
	@Autowired
	IUserService userServices;
	
	@Autowired
	IProblemService problemService;
	
	@Autowired
	ISolutionScoreService solutionScoreService;
	
	@Autowired
	IValueService valueService;
	
	@Autowired
	ISolutionService solutionService;
	
	@Autowired
	IValueScoreService valueScoreService;
	
	@Autowired
	ValueRepository valueRepository;
	
	@Autowired
	IVersusResponseService versusResponseService;
	
	@Autowired
	IValueSolutionScoreService valueSolutionScoreService;
	
	@Autowired
	VersusRepository versusRepository;
	
	@Autowired
	ICommentService commentService;
	
	@Autowired
	IVersusService versusService;
	
	@Autowired
	SolutionRepository solutionRepository;

	
	@RequestMapping("/")
	@ResponseBody
    String test() {
		
        return "It's alive !";
    }
	
	@RequestMapping(method=RequestMethod.GET, value="/user")
	@ResponseBody User getUser(@RequestParam(value= "id", required=true) long id){
		return this.userRepository.findOne(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/user")
	@ResponseBody User createUser(@RequestParam(value="mail", required=true) String email, 
			@RequestParam("username") String userName){
		System.out.println("Post user");
		return this.userServices.createUser(email, userName);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/problems")
	@ResponseBody Iterable<Problem>  getProblem(){
		System.out.println("Get problems");
		return this.problemService.getAllProblems();

	}
	
	@RequestMapping(method=RequestMethod.POST, value="/problems")
	@ResponseBody boolean  createProblem(@RequestParam(value = "name", required = true) String name, 
			@RequestParam(value = "description", required = true)String description){
		System.out.println("Post problems");
		return this.problemService.createProblem(name, description);
		
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/problems/{id:[\\d]+}/values")
	@ResponseBody Collection<Value> getValuesForProblem(@PathVariable("id") long id){
		return (Collection<Value>) this.problemService.getValuesForProblem(id);
	}
	
	     
	@RequestMapping(method=RequestMethod.POST, value="/problems/{id}/values")
	@ResponseBody Value createValueForProblem(@PathVariable("id") long id, @RequestParam("name") String name, @RequestParam("description") String description){
		System.out.println("Post value for problem");
		Value value = this.valueService.createValue(name, description);
		return this.problemService.AddValue(id, value);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/problems/{id:[\\d]+}/solutions")
	@ResponseBody Collection<Solution> getSolutionsForProblem(@PathVariable("id") long id){
		Problem p = this.problemRepository.findOne(id);
		return this.solutionService.getSolutionsForProblem(p);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/problems/{id}/solutions")
	@ResponseBody Solution createSolutionForProblem(@PathVariable("id") long id,@RequestParam("name") String name,
			@RequestParam("description")String description ){
		Problem p = this.problemRepository.findOne(id);
		//créer solution
		Solution solution = this.solutionService.createSolution(name, description, p);
		return solution;
	}
	@RequestMapping(method=RequestMethod.POST, value="problems/{id}/solutions/select")
	@ResponseBody void selectSolutionsForProblem(@PathVariable("id")long problemId, @RequestParam("userId") long userId,
	@RequestParam("solutionsId") long[] solutionsId){
		//initialiser les solution score de l'utilisateur pour les solutions associées à 0
		// créer les versus correspondants s'ils n'existent pas
		System.out.println("select solutions");
		User user = this.userRepository.findOne(userId);
		List<Solution> selectedSolutions = new ArrayList<Solution>();
		if ( user != null){
			for ( int i = 0 ; i < solutionsId.length; i ++ ){
				Solution solution = this.solutionRepository.findOne(solutionsId[i]);
				if ( solution != null ){
					selectedSolutions.add(solution);
					this.solutionScoreService.initializeSolutionScore(user, solution);
				}
			}
		}
		Problem problem = this.problemRepository.findOne(problemId);
		this.versusService.createMissingVersuses(problem, selectedSolutions);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/values/{id}/valuescores")
	@ResponseBody ValueScore createValueScore (@PathVariable ("id") long id, @RequestParam double score, @RequestParam long userId){
		//TODO : gerer l'authentification
		System.out.println("Create score for value");
		Value value = this.valueRepository.findOne(id);
		User user = this.userRepository.findOne(userId);
		return this.valueScoreService.createValueScore(score, value, user);
		
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/problems/{id}/versus/next")
	@ResponseBody Versus getNextVersusForProblem(@PathVariable("id") long id, @RequestParam("userId") long userId){
		System.out.println("Get next versus for problem");
		Problem problem = this.problemRepository.findOne(id);
		User user = this.userRepository.findOne(userId);
		return this.versusService.getNextVersus(problem, user); //TODO : auth, verifier qu'on renvoie bien le bon truc
		
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/versus/{id}/versusresponses")
	@ResponseBody VersusResponse createVersusResponse (@PathVariable("id") long id, 
			@RequestParam("response") double response, @RequestParam ("userId") long userId){
		System.out.println("Post response for versus");
		Versus versus = this.versusRepository.findOne(id);
		User user = this.userRepository.findOne(userId);
		return this.versusResponseService.createVersusResponse(response, versus, user);
		//TODO : authentification
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/problems/{id}/solutionscores")
	@ResponseBody Collection<SolutionScore> getSolutionScoreForProblem(@PathVariable("id") long id, @RequestParam("userId") long userId ){
		System.out.println("Get SolutionScores for problem");
		Problem problem = this.problemRepository.findOne(id);
		User user = this.userRepository.findOne(userId);
		//TODO : return both
		/*return (Collection<ValueSolutionScore>) */valueSolutionScoreService.createValueSolutionScores(problem, user);
		return (Collection<SolutionScore>) solutionScoreService.fillSolutionScores(problem, user);
	}
	
	
	@RequestMapping(method=RequestMethod.GET, value="/versus/{id}/comments")
	@ResponseBody Collection<Comment> getCommentsForVersus(@PathVariable("id") Long id){
		System.out.println("Get comments for versus");
		Versus versus = this.versusRepository.findOne(id);
		return this.commentService.getCommentsForVersus(versus);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/versus/{id}/comments")
	@ResponseBody Comment createCommentForVersus(@PathVariable("id") long id, @RequestParam("name") String name
			, @RequestParam("content") String content, @RequestParam("userId")long userId){
		System.out.println("Post comment for versus");
		User user = this.userRepository.findOne(userId);
		Versus versus = this.versusRepository.findOne(id);
		return this.commentService.createComment(name, content, versus, user);
		//TODO : auth
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/comments/{id}/comments")
	@ResponseBody Comment respondToComment(@PathVariable("id") long id, @RequestParam("name") String name
			, @RequestParam("content") String content, @RequestParam("userId")long userId){
		System.out.println("Post a response to a comment");
		User user = this.userRepository.findOne(userId);
		//Versus versus = this.versusRepository.findOne(id);
		//return this.commentService.createComment(name, content, versus, user);
		//TODO : auth
		return this.commentService.respondToComment(name, content, id, user);
	}

}
