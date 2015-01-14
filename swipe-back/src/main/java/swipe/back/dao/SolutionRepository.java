package swipe.back.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import swipe.back.domain.Problem;
import swipe.back.domain.Solution;
import swipe.back.domain.User;

@Repository
public interface SolutionRepository extends CrudRepository<Solution, Long> {

	/**
	 * Renvoie toutes les solutions d'un problem pour lesquelles il existe un solutionScore appartenant à l'user.
	 * @param user
	 * @return
	 */
	public Iterable<Solution> findForUserAndProblem(User user, Problem problem);
}
