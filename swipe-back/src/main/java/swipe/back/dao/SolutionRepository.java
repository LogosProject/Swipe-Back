package swipe.back.dao;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import swipe.back.domain.Problem;
import swipe.back.domain.Solution;
import swipe.back.domain.User;

@Repository
public interface SolutionRepository extends CrudRepository<Solution, Long> {

	Collection<Solution> findByProblem(Problem p);
}
