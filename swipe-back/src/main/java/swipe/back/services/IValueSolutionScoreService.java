package swipe.back.services;

import swipe.back.domain.Problem;
import swipe.back.domain.Solution;
import swipe.back.domain.User;
import swipe.back.domain.Value;
import swipe.back.domain.ValueSolutionScore;

public interface IValueSolutionScoreService {
	
	/**
	 * Calcule le score d'un valueSolutionScore et le persiste en BDD
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
	
	
	/**
	 * Retourne la ValueSolutionScore correspondant aux paramètres d'entrée. En crée une si elle n'en trouve pas.
	 * @param user
	 * @param value
	 * @param Solution
	 * @return
	 */
	public ValueSolutionScore getOrCreateValueSolutionScore(User user, Value value, Solution solution);
}
