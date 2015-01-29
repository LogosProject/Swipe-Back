package swipe.back.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import swipe.back.dao.SolutionRepository;
import swipe.back.domain.Problem;
import swipe.back.domain.Solution;
import swipe.back.domain.SolutionScore;
import swipe.back.domain.User;
import swipe.back.domain.ValueSolutionScore;

@Service
public class SolutionService implements ISolutionService {

	@Autowired
	SolutionRepository solutionRepository;
	
	@Override
	public void createDependingVersus(Solution solution) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ValueSolutionScore[] createValueSolutionScores(Solution solution,
			User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SolutionScore createSolutionScore(Solution solution, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Solution> getSolutionsForProblem(Problem p) {
		return this.solutionRepository.findByProblem(p);
		
	}

	@Override
	public Solution createSolution(String name, String description, Problem problem) {
		Solution solution = new Solution();
		solution.setName(name);
		solution.setDescription(description);
		solution.setProblem(problem);
		this.solutionRepository.save(solution);
		return solution;
	}

}
