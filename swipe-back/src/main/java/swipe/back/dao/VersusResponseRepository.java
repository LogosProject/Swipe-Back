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

	@Query("SELECT vr FROM VersusResponse vr WHERE vr.user = :user AND vr.versus IN (SELECT ve.id from versus ve WHERE ve.problem = :problem)")
	Iterable<VersusResponse> findForUserAndProblem(
			@Param("user") User user, @Param("problem") Problem problem);

}
