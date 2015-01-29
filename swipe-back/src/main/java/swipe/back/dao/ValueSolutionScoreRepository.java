package swipe.back.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import swipe.back.domain.Problem;
import swipe.back.domain.Solution;
import swipe.back.domain.User;
import swipe.back.domain.Value;
import swipe.back.domain.ValueSolutionScore;
import swipe.back.domain.VersusResponse;

@Repository
public interface ValueSolutionScoreRepository extends CrudRepository<ValueSolutionScore, Long> {

	ValueSolutionScore findByUserAndValueAndSolution(User user, Value value,
			Solution solution);

	@Query("SELECT vsc FROM ValueSolutionScore vsc WHERE vsc.user = :user AND vsc.solution IN (SELECT s.id from Solution s WHERE s.problem = :problem)")
	Iterable<ValueSolutionScore> findForUserAndProblem(
			@Param("user") User user, @Param("problem") Problem problem);

}
