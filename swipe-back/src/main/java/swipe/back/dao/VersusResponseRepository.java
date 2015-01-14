package swipe.back.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import swipe.back.domain.Problem;
import swipe.back.domain.User;
import swipe.back.domain.VersusResponse;

@Repository
public interface VersusResponseRepository extends CrudRepository<VersusResponse, Long> {

	@Query("SELECT v FROM VersusResponse v WHERE v.user = :user AND v.versus IN (SELECT vs.id from versus vs WHERE vs.problem = :problem)")
	Iterable<VersusResponse> findForUserAndProblem(
			@Param("user") User user, @Param("problem") Problem problem);
}
