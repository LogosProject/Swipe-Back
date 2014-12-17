package swipe.back.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import swipe.back.domain.Versus;

@Repository
public interface VersusRepository extends CrudRepository<Versus, Long> {

}
