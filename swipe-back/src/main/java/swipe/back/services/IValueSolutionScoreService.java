package swipe.back.services;

import swipe.back.domain.ValueSolutionScore;

public interface IValueSolutionScoreService {
	
	/**
	 * Calcule le score d'un valueSolutionScore
	 * 
	 * @param valueSolutionScore
	 * @return double
	 */
	public double calculateScore(ValueSolutionScore valueSolutionScore);
}
