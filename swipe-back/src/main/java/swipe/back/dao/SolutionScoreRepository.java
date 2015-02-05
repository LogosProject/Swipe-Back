package swipe.back.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import swipe.back.domain.Problem;
import swipe.back.domain.Solution;
import swipe.back.domain.SolutionScore;
import swipe.back.domain.User;
import swipe.back.domain.VersusResponse;

@Repository
public interface SolutionScoreRepository extends CrudRepository<SolutionScore, Long> {

	@Query("SELECT vr FROM SolutionScore vr WHERE vr.user = :user AND vr.solution IN (SELECT s.id from Solution s WHERE s.problem = :problem)")
	Iterable<SolutionScore> findForUserAndProblem(
			@Param("user") User user, @Param("problem") Problem problem);
	
	public SolutionScore findByUserAndSolution(User user, Solution solution);
}
