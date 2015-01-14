package swipe.back.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import swipe.back.dao.ValueScoreRepository;
import swipe.back.dao.ValueSolutionScoreRepository;
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
	
	@Override
	public double calculateScore(ValueSolutionScore valueSolutionScore) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public Iterable<ValueSolutionScore> createValueSolutionScores(
			Problem problem, User user) {
	
		Iterable<ValueScore> valueScores = valueScoreRepository.findForUserAndProblem(user, problem);
		Iterable<VersusResponse> versusResponses = versusResponseRepository.findForUserAndProblem(user, problem);
	
		for (ValueScore valueScore : valueScores) {
			Value value = valueScore.getValue();
			for(VersusResponse versusResponse : versusResponses) {
				Versus versus = versusResponse.getVersus();
				ValueSolutionScore valueSolutionScore1 = getOrCreateValueSolutionScore(user, value, versus.getSolution1());
				ValueSolutionScore valueSolutionScore2 = getOrCreateValueSolutionScore(user, value, versus.getSolution2());
				
				calculateScore(valueSolutionScore1);
				calculateScore(valueSolutionScore2);
			}
		}
		
		ArrayList<ValueSolutionScore> valueSolutionScore = new ArrayList<ValueSolutionScore>();
		
		return valueSolutionScore;
	}
	
	/**
	 * Retourne la ValueSolutionScore correspondant aux paramètres d'entrée. En crée une si elle n'en trouve pas.
	 * @param user
	 * @param value
	 * @param Solution
	 * @return
	 */
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
