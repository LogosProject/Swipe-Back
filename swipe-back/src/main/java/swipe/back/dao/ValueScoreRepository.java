package swipe.back.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import swipe.back.domain.Problem;
import swipe.back.domain.User;
import swipe.back.domain.Value;
import swipe.back.domain.ValueScore;
import swipe.back.domain.VersusResponse;

@Repository
public interface ValueScoreRepository extends CrudRepository<ValueScore, Long> {


	ValueScore findByUserAndValue(User user, Value value);
	
	@Query("SELECT vs FROM ValueScore vs WHERE vs.user = :user AND vs.value = (SELECT v from Value v WHERE v.problem = :problem)")
	Iterable<ValueScore> findForUserAndProblem(
			@Param("user") User user, @Param("problem") Problem problem);

}
