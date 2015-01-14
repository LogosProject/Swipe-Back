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
import swipe.back.domain.VersusResponse;

public class ValueSolutionScoreService implements IValueSolutionScoreService {
	
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
			for(VersusResponse versusResponse : versusResponses) {
				//getOrCreate
			}
		}
		
		ArrayList<ValueSolutionScore> valueSolutionScore = new ArrayList<ValueSolutionScore>();
		
		return valueSolutionScore;
	}
}
