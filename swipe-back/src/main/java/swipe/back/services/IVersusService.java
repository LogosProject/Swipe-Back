package swipe.back.services;

import java.util.List;

import swipe.back.domain.Problem;
import swipe.back.domain.Solution;
import swipe.back.domain.User;
import swipe.back.domain.Versus;

public interface IVersusService {
	
	/**
	 * Récupère le prochain versus à traiter d'un utilisateur donné
	 * @param problem
	 * @param user
	 * @return
	 */
	public Versus getNextVersus(Problem problem, User user);
	
	public void createMissingVersuses(Problem problem, List<Solution>solutions );
}
