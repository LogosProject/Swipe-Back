package swipe.back.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import swipe.back.domain.VersusResponse;

@Repository
public interface VersusResponseRepository extends CrudRepository<VersusResponse, Long> {

}
