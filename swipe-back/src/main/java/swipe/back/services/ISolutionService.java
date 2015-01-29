package swipe.back.services;

import java.util.Collection;

import swipe.back.domain.Problem;
import swipe.back.domain.Solution;
import swipe.back.domain.SolutionScore;
import swipe.back.domain.User;
import swipe.back.domain.ValueSolutionScore;

public interface ISolutionService {
	
	/**
	 * Crée tous les Versus après l'ajout d'une solution.
	 * Cette fonction peut être réalisée de manière asynchrone
	 * 
	 * @param solution
	 */
	public void createDependingVersus(Solution solution);
	
	/**
	 * Crée ou modifie tous les valueSolutionScore d'une solution et d'un utilisateur donné
	 * 
	 * @param solution
	 * @param user
	 * @return
	 */
	public ValueSolutionScore[] createValueSolutionScores(Solution solution, User user);
	
	/**
	 * Crée ou modifie tous la solutionScore d'une solution et d'un utilisateur donné
	 * 
	 * @param solution
	 * @param user
	 * @return
	 */
	public SolutionScore createSolutionScore(Solution solution, User user);
	
	public Collection<Solution> getSolutionsForProblem(Problem p);
	
	public Solution createSolution (String name, String description, Problem problem);
	
}
