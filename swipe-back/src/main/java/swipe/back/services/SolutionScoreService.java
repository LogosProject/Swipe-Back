package swipe.back.services;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import swipe.back.dao.SolutionScoreRepository;
import swipe.back.dao.ValueScoreRepository;
import swipe.back.dao.ValueSolutionScoreRepository;
import swipe.back.domain.Problem;
import swipe.back.domain.SolutionScore;
import swipe.back.domain.User;
import swipe.back.domain.Value;
import swipe.back.domain.ValueScore;
import swipe.back.domain.ValueSolutionScore;
import swipe.back.domain.Versus;
import swipe.back.domain.VersusResponse;

public class SolutionScoreService implements ISolutionScoreService {

	@Autowired
	SolutionScoreRepository solutionScoreRepository;
	
	@Autowired
	ValueSolutionScoreRepository valueSolutionScoreRepository;
	
	@Autowired
	ValueScoreRepository valueScoreRepository;

	@Override
	public Iterable<SolutionScore> createSolutionScores(Problem problem,
			User user) {
		
		Iterable<SolutionScore> solutionScores = solutionScoreRepository.findForUserAndProblem(user, problem);
		Iterable<ValueScore> valueScores = valueScoreRepository.findForUserAndProblem(user, problem);
		
		for (SolutionScore solutionScore : solutionScores) {
			solutionScore.setScore(0);
			for (ValueScore valueScore : valueScores) {
				ValueSolutionScore valueSolutionScore = valueSolutionScoreRepository.findByUserAndValueAndSolution(user, valueScore.getValue(), solutionScore.getSolution());
				if (valueSolutionScore != null) {
					double scoreToAdd = solutionScore.getScore()*valueSolutionScore.getScore();
					solutionScore.setScore(solutionScore.getScore()+scoreToAdd);
				}
			}
		}
		
		return solutionScores;
	}

}
