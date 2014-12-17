package swipe.back.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import swipe.back.domain.Value;

@Repository
public interface ValueRepository extends CrudRepository<Value, Long> {

}
