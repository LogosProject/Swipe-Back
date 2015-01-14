package swipe.back.services;

import swipe.back.domain.Problem;
import swipe.back.domain.User;
import swipe.back.domain.SolutionScore;

public interface ISolutionScoreService {
	
	/**
	 * Calcule le score d'un solutionScore
	 * 
	 * @param solutionScore
	 * @return double
	 */
	public double calculateScore(SolutionScore solutionScore);
	
	/**
	 * Crée ou modifie tous les solutionScore d'un utilisateur donné
	 * @param problem
	 * @param user
	 * @return
	 */
	public Iterable<SolutionScore> createSolutionScores(Problem problem, User user);

}
