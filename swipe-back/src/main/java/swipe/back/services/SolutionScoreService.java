package swipe.back.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import swipe.back.dao.ProblemRepository;
import swipe.back.dao.SolutionRepository;
import swipe.back.dao.SolutionScoreRepository;
import swipe.back.dao.ValueRepository;
import swipe.back.dao.ValueScoreRepository;
import swipe.back.dao.ValueSolutionScoreRepository;
import swipe.back.domain.Problem;
import swipe.back.domain.Solution;
import swipe.back.domain.SolutionScore;
import swipe.back.domain.User;
import swipe.back.domain.Value;
import swipe.back.domain.ValueScore;
import swipe.back.domain.ValueSolutionScore;
import swipe.back.domain.Versus;
import swipe.back.domain.VersusResponse;

@Service
public class SolutionScoreService implements ISolutionScoreService {

	@Autowired
	SolutionScoreRepository solutionScoreRepository;
	
	@Autowired
	ValueSolutionScoreRepository valueSolutionScoreRepository;
	
	@Autowired
	ValueScoreRepository valueScoreRepository;
	
	@Autowired
	ValueRepository valueRepository;
	
	
	@Autowired
	ProblemRepository problemRepository;
	
	@Autowired
	SolutionRepository solutionRepository;
	
	@Override
	public Iterable<SolutionScore> fillSolutionScores(Problem problem,
			User user) {
		
		Iterable<SolutionScore> solutionScores = solutionScoreRepository.findForUserAndProblem(user, problem);
		
		Iterable<Value> values = valueRepository.findByProblem(problem);
		
		//Iterable<ValueScore> valueScores = valueScoreRepository.findForUserAndProblem(user, problem);
		Iterable<ValueScore> valueScores = new ArrayList<ValueScore>();
		for (Value value : values ){
			((ArrayList<ValueScore>) valueScores).add(valueScoreRepository.findByUserAndValue(user, value));
		}
		
		for (SolutionScore solutionScore : solutionScores) {
			solutionScore.setScore(0);
			for (ValueScore valueScore : valueScores) {
				ValueSolutionScore valueSolutionScore = valueSolutionScoreRepository.findByUserAndValueAndSolution(user, valueScore.getValue(), solutionScore.getSolution());
				if (valueSolutionScore != null) {
					double scoreToAdd = solutionScore.getScore()*valueSolutionScore.getScore();
					solutionScore.setScore(solutionScore.getScore()+scoreToAdd);
					solutionScoreRepository.save(solutionScore);
				}
			}
		}
		
		return solutionScores;
	}

	@Override
	public void initializeSolutionScore(User user, Solution solution) {
		
		
		SolutionScore newSolutionScore = new SolutionScore();
		newSolutionScore.setScore(0);
		newSolutionScore.setSolution(solution);
		newSolutionScore.setUser(user);
		this.solutionScoreRepository.save(newSolutionScore);
	}

}
