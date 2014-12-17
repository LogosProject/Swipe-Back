package swipe.back.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import swipe.back.dao.ProblemRepository;
import swipe.back.dao.UserRepository;
import swipe.back.domain.Comment;
import swipe.back.domain.Problem;
import swipe.back.domain.Solution;
import swipe.back.domain.SolutionScore;
import swipe.back.domain.User;
import swipe.back.domain.Value;
import swipe.back.domain.VersusResponse;

//@EnableAutoConfiguration
@Controller
public class WebController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ProblemRepository problemRepository;
	
	@RequestMapping("/test")
	@ResponseBody
    String test() {
		User u = new User(123, "toto","toto");
		u.setPassword("password");
		u.setUsername("toto");
		userRepository.save(u);
		//problemRepository.save(new Problem(123, "toto", "toto"));
        return "Hello World!";
    }
	
	@RequestMapping(method=RequestMethod.GET, name="/user")
	@ResponseBody User getUser(@RequestParam("id") long id){
		//TODO : récupérer un utilisateur
		System.out.println("Get user");
		return new User(123,"toto", "toto");
	}
	
	@RequestMapping(method=RequestMethod.POST, name="/user")
	void createUser(@RequestParam("id") long id){
		System.out.println("Post user");
		//TODO : créer u utilisaterur si utilisateur inexistant, ne rien faire si non (? ou mettre à jour ?)
	}
	
	@RequestMapping(method=RequestMethod.GET, name="/problems")
	@ResponseBody Problem  getProblem(@RequestParam("id") long id){
		//TODO : récupérer une problematique
		System.out.println("Get problems");
		return new Problem(123,"toto", "toto");
	}
	
	@RequestMapping(method=RequestMethod.POST, name="/problems")
	@ResponseBody void  createProblem(@RequestParam("id") long id){
		System.out.println("Post problems");
		//TODO : créer une problématique
		
	}
	
	@RequestMapping(method=RequestMethod.GET, name="/problems/{id}/values")
	@ResponseBody Collection<Value> getValuesForProblem(@PathVariable long id){
		//TODO : récupérer les valeurs correspondant au probleme
		System.out.println("Get values for problem");
		return new ArrayList<Value>();
	}
	
	@RequestMapping(method=RequestMethod.POST, name="/problems/{id}/values")
	@ResponseBody void createValueForProblem(@PathVariable long id){
		System.out.println("Post value for problem");
		//TODO : créer une valeur pour le probleme correspondant
	}
	
	@RequestMapping(method=RequestMethod.GET, name="/problems/{id}/solutions")
	@ResponseBody Collection<Solution> getSolutionsForProblem(@PathVariable("id") long id){
		//TODO : récupérer les solutions correspondant au probleme
		System.out.println("Get solutions for problem");
		return new ArrayList<Solution>();
	}
	
	@RequestMapping(method=RequestMethod.POST, name="/problems/{id}/solutions")
	@ResponseBody void createSolutionForProblem(@PathVariable("id") long id){
		//TODO : créer une solution pour le probleme correspondant
		System.out.println("Create solution for problem");
	}
	
	@RequestMapping(method=RequestMethod.POST, name="/values/{id}/valuescores")
	@ResponseBody void createValueScore (@PathVariable long id){
		//TODO : créer un valuescore pour la valeur correspondante
		System.out.println("Create score for value");
	}
	
	@RequestMapping(method=RequestMethod.GET, name="/problems/{id}/versus/next")
	@ResponseBody VersusResponse getNextVersusForProblem(@PathVariable long id){
		//TODO : récupérer le versus suivant pour le probleme donné
		System.out.println("Get next versus for problem");
		return new VersusResponse();
	}
	
	@RequestMapping(method=RequestMethod.POST, name="/versus/{id}/versusresponses")
	@ResponseBody void createVersusResponse (@PathVariable long id){
		System.out.println("Post response for versus");
		//TODO : créer un VersusResponses
	}
	
	@RequestMapping(method=RequestMethod.GET, name="/problems/{id}/solutionscores")
	@ResponseBody Collection<SolutionScore> getSolutionScoreForProblem(@PathVariable long id){
		System.out.println("Get SolutionScores for problem");
		//TODO : récupérer les solutionscores pour le probleme correspondant
		return new ArrayList<SolutionScore>();
	}
	
	@RequestMapping(method=RequestMethod.GET, name="/versus/{id}/comments")
	@ResponseBody Collection<Comment> getCommentsForVersus(@PathVariable Long id){
		System.out.println("Get comments for versus");
		//TODO : récupérer les commentaires pour le versus
		return new ArrayList<Comment>();
	}
	
	@RequestMapping(method=RequestMethod.POST, name="/versus/{id}/comments")
	@ResponseBody void createCommentForVersus(@PathVariable Long id){
		System.out.println("Post comment for versus");
		//TODO : créer un commentaire pour le versus
	}
	
	@RequestMapping(method=RequestMethod.POST, name="/comments/{id}/comments")
	@ResponseBody void respondToComment(@PathVariable long id){
		System.out.println("Post a response to a comment");
		//TODO : créer le commentaire correspondant
	}
	
}