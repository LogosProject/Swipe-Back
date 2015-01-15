package swipe.back.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import swipe.back.domain.Problem;
import swipe.back.domain.SolutionScore;
import swipe.back.domain.User;
import swipe.back.domain.VersusResponse;

@Repository
public interface SolutionScoreRepository extends CrudRepository<SolutionScore, Long> {

	@Query("SELECT ss FROM SolutionScore vr WHERE ss.user = :user AND ss.solution IN (SELECT s.id from solution s WHERE s.problem = :problem)")
	Iterable<SolutionScore> findForUserAndProblem(
			@Param("user") User user, @Param("problem") Problem problem);
}
