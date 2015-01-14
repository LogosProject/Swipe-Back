package swipe.back.services;

import swipe.back.domain.Problem;
import swipe.back.domain.User;
import swipe.back.domain.ValueSolutionScore;

public interface IValueSolutionScoreService {
	
	/**
	 * Calcule le score d'un valueSolutionScore
	 * 
	 * @param valueSolutionScore
	 * @return double
	 */
	public double calculateScore(ValueSolutionScore valueSolutionScore);
	
	/**
	 * Crée ou modifie tous les valueSolutionScore d'un utilisateur donné
	 * @param problem
	 * @param user
	 * @return
	 */
	public Iterable<ValueSolutionScore> createValueSolutionScores(Problem problem, User user);
}
