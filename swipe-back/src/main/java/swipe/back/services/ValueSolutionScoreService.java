package swipe.back.services;
import java.util.ArrayList;

import lombok.extern.java.Log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

@Service
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
		double score = 0;
		
		
		
		
		valueSolutionScore.setScore(score);
		return score;
	}
	
	@Override
	public Iterable<ValueSolutionScore> createValueSolutionScores(
			Problem problem, User user) {
	
		Iterable<ValueScore> valueScores = valueScoreRepository.findForUserAndProblem(user, problem);
		
		Iterable<VersusResponse> versusResponses = versusResponseRepository.findForUserAndProblem(user, problem);
		ArrayList<ValueSolutionScore> valueSolutionScores = new ArrayList<ValueSolutionScore>();

		for(VersusResponse versusResponse : versusResponses) {
			Versus versus = versusResponse.getVersus();
			ValueSolutionScore valueSolutionScore1 = this.getOrCreateValueSolutionScore(user, versus.getValue(), versus.getSolution1());
			ValueSolutionScore valueSolutionScore2 = this.getOrCreateValueSolutionScore(user, versus.getValue(), versus.getSolution2());
			
			//valueSolutionScore1.setScore(1);
			//valueSolutionScore2.setScore(1);
			
			valueSolutionScores.add(valueSolutionScore1);
			valueSolutionScores.add(valueSolutionScore2);
		}
		
		return computePointsValueSolutionScores(valueSolutionScores, versusResponses, valueScores);
	}
	
	public Iterable<ValueSolutionScore> computePointsValueSolutionScores(Iterable<ValueSolutionScore> valueSolutionScores,
												Iterable<VersusResponse> versusResponses, Iterable<ValueScore> valueScores) {
		System.out.println("BEGIN");
		ArrayList<Solution> solutions = new ArrayList<Solution>();
		
		double alpha = 0.1;
		
		for (ValueScore valueScore : valueScores) {
			Value value = valueScore.getValue();
			System.out.println("value loop");
			for (int i = 0; i<1000; i++) {
				for (VersusResponse versusResponse : versusResponses) {
					System.out.println("versus" + i);
					Versus versus = versusResponse.getVersus();
					ValueSolutionScore sria = this.getOneVSS(valueSolutionScores, value, versus.getSolution1());
					ValueSolutionScore srib = this.getOneVSS(valueSolutionScores, value, versus.getSolution2());
					
					double p = 10/(0.01 + Math.exp(sria.getScore() - srib.getScore()) ) - 5;
					double err = p - versusResponse.getResponse();
					
					sria.setScore(sria.getScore() + alpha*err);
					srib.setScore(srib.getScore() - alpha*err);
					
					// Ajouter les solutions envisagées à la liste
					if (!solutions.contains(versus.getSolution1())) {
						solutions.add(versus.getSolution1());
					}
					if (!solutions.contains(versus.getSolution2())) {
						solutions.add(versus.getSolution2());
					}
				}
			}
			
			// persister les valeurs
			for (ValueSolutionScore valueSolutionScore : valueSolutionScores) {
				valueSolutionScoreRepository.save(valueSolutionScore);
			}
			
			double squareSumScore = 0;
			
			for (ValueSolutionScore valueSolutionScore : valueSolutionScores) {
				squareSumScore += valueSolutionScore.getScore() * valueSolutionScore.getScore();
			}
			
			double std = Math.sqrt(squareSumScore);
					
			for (Solution solution : solutions) {
				ValueSolutionScore sri = this.getOneVSS(valueSolutionScores, value, solution);
				sri.setScore(sri.getScore() / std);
				System.out.println("FINAL SCORE : " + sri.getScore());
				valueSolutionScoreRepository.save(sri);
			}
		}
		System.out.println("END");
		return valueSolutionScores;
	}
	
	public ValueSolutionScore getOneVSS(Iterable<ValueSolutionScore> valueSolutionScores, Value value, Solution solution) {
		for (ValueSolutionScore valueSolutionScore : valueSolutionScores) {
			if (valueSolutionScore.getValue() == value && valueSolutionScore.getSolution() == solution) {
				return valueSolutionScore;
			}
		}
		return null;
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
