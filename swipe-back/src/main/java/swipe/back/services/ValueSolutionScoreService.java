package swipe.back.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import swipe.back.dao.SolutionRepository;
import swipe.back.dao.ValueRepository;
import swipe.back.domain.Problem;
import swipe.back.domain.Solution;
import swipe.back.domain.User;
import swipe.back.domain.Value;
import swipe.back.domain.ValueSolutionScore;

public class ValueSolutionScoreService implements IValueSolutionScoreService {

	@Autowired
	SolutionService solutionService;
	
	@Autowired
	SolutionRepository solutionRepository;
	
	@Autowired
	ValueRepository valueRepository;
	
	@Override
	public double calculateScore(ValueSolutionScore valueSolutionScore) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public Iterable<ValueSolutionScore> createValueSolutionScores(
			Problem problem, User user) {
	
		Iterable<Solution> solutions = solutionRepository.findForUserAndProblem(user, problem);
		Iterable<Value> values = valueRepository.findForUserAndProblem(user, problem);
		
		for(Solution solution : solutions) {
			
		}

		Iterable<ValueSolutionScore> valueSolutionScore = new ArrayList<ValueSolutionScore>();
		
		return valueSolutionScore;
	}
}
