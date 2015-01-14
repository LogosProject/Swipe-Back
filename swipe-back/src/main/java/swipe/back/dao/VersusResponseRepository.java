package swipe.back.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import swipe.back.domain.Problem;
import swipe.back.domain.User;
import swipe.back.domain.VersusResponse;

@Repository
public interface VersusResponseRepository extends CrudRepository<VersusResponse, Long> {

	Iterable<VersusResponse> findForUserAndProblem(
		User user, Problem problem);

}
