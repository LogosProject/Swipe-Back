package swipe.back.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import swipe.back.dao.ProblemRepository;
import swipe.back.dao.SolutionRepository;
import swipe.back.dao.SolutionScoreRepository;
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
	ProblemRepository problemRepository;
	
	@Autowired
	SolutionRepository solutionRepository;

	
	
	
	@Override
	public Iterable<SolutionScore> createSolutionScores(Problem problem,
			User user) {
		
		Iterable<SolutionScore> solutionScores = solutionScoreRepository.findForUserAndProblem(user, problem);
		
		Iterable<Value> values = problem.getValues();
		
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
				}
			}
		}
		
		return solutionScores;
	}




	@Override
	public Iterable<SolutionScore> getSolutionScores(Problem problem, User user) {
		//obtenir toutes les solutions du probleme
		Collection<Solution> solutions = this.solutionRepository.findByProblem(problem);
		//pour chaque solution, obtenir les valuesolutionscore correspondant à l'utilisateur, la solution et chaque valeurs du probleme
		
		for ( Solution solution : solutions ){
			double score = 0;
			for ( Value value : problem.getValues() ){
				ValueSolutionScore valueSolutionScore = this.valueSolutionScoreRepository.findByUserAndValueAndSolution(user, value, solution);
				score += valueSolutionScore.getScore();
			}
			SolutionScore solutionScore = this.solutionScoreRepository.findByUserAndSolution(user, solution);
			solutionScore.setScore(score);
			this.solutionScoreRepository.save(solutionScore);
		}
		return this.solutionScoreRepository.findForUserAndProblem(user, problem);
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
