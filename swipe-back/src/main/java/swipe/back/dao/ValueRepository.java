package swipe.back.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import swipe.back.domain.Problem;
import swipe.back.domain.Solution;
import swipe.back.domain.User;
import swipe.back.domain.Value;

@Repository
public interface ValueRepository extends CrudRepository<Value, Long> {
	
	/**
	 * Renvoie toutes les value d'un problem pour lesquelles il existe un valueScore appartenant à l'user.
	 * @param user
	 * @return
	 */
	public Iterable<Value> findForUserAndProblem(User user, Problem problem);
}
