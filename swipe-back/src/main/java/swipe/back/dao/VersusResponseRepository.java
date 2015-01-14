package swipe.back.dao;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import swipe.back.domain.Problem;
import swipe.back.domain.User;
import swipe.back.domain.Versus;
import swipe.back.domain.VersusResponse;

@Repository
public interface VersusResponseRepository extends CrudRepository<VersusResponse, Long> {

	Iterable<VersusResponse> findByUserAndVersusIn( User user, Iterable<Versus> versusList);
	
}
