package swipe.back.services;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import swipe.back.dao.ProblemRepository;
import swipe.back.dao.ValueRepository;
import swipe.back.dao.ValueScoreRepository;
import swipe.back.dao.ValueSolutionScoreRepository;
import swipe.back.dao.VersusRepository;
import swipe.back.dao.VersusResponseRepository;
import swipe.back.domain.Problem;
import swipe.back.domain.Solution;
import swipe.back.domain.User;
import swipe.back.domain.Value;
import swipe.back.domain.ValueScore;
import swipe.back.domain.ValueSolutionScore;
import swipe.back.domain.Versus;
import swipe.back.domain.VersusResponse;

public class ValueSolutionScoreService implements IValueSolutionScoreService {
	
	@Autowired
	ValueSolutionScoreRepository valueSolutionScoreRepository;
	
	@Autowired
	VersusResponseRepository versusResponseRepository;
	
	@Autowired
	ValueScoreRepository valueScoreRepository;
	
	@Autowired
	VersusRepository versusRepository;
	
	@Autowired
	ProblemRepository problemRepository;
	
	@Autowired
	ValueRepository valueRepository;
	
	@Override
	public double calculateScore(ValueSolutionScore valueSolutionScore) {
		double score = -1;
		
		// TODO Code métier		
		
		valueSolutionScore.setScore(score);
		return score;
	}
	
	@Override
	public Iterable<ValueSolutionScore> createValueSolutionScores(
			Problem problem, User user) {
	
		//Iterable<ValueScore> valueScores = valueScoreRepository.findForUserAndProblem(user, problem);
		
		Iterable<Value> values = problem.getValues();
		Iterable<ValueScore> valueScores = new ArrayList<ValueScore>();
		for (Value value : values ){
			((ArrayList<ValueScore>) valueScores).add(valueScoreRepository.findByUserAndValue(user, value));
		}
		Iterable<VersusResponse> versusResponses = versusResponseRepository.findForUserAndProblem(user, problem);
		ArrayList<ValueSolutionScore> valueSolutionScores = new ArrayList<ValueSolutionScore>();

		for (ValueScore valueScore : valueScores) {
			Value value = valueScore.getValue();
			for(VersusResponse versusResponse : versusResponses) {
				Versus versus = versusResponse.getVersus();
				ValueSolutionScore valueSolutionScore1 = getOrCreateValueSolutionScore(user, value, versus.getSolution1());
				ValueSolutionScore valueSolutionScore2 = getOrCreateValueSolutionScore(user, value, versus.getSolution2());
				
				calculateScore(valueSolutionScore1);
				calculateScore(valueSolutionScore2);
				
				valueSolutionScores.add(valueSolutionScore1);
				valueSolutionScores.add(valueSolutionScore2);
			}
		}
		
		return valueSolutionScores;
	}
	
	public ValueSolutionScore getOrCreateValueSolutionScore(User user, Value value, Solution solution) {
		
		ValueSolutionScore valueSolutionScore = valueSolutionScoreRepository.findByUserAndValueAndSolution(user, value, solution);
		
		if (valueSolutionScore == null) {
			valueSolutionScore = new ValueSolutionScore();
			valueSolutionScore.setUser(user);
			valueSolutionScore.setValue(value);
			valueSolutionScore.setSolution(solution);
			
			valueSolutionScoreRepository.save(valueSolutionScore);
		}
		
		return valueSolutionScore;
	}
}
