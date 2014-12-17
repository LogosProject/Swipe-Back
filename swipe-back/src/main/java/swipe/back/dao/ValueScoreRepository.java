package swipe.back.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import swipe.back.domain.ValueScore;

@Repository
public interface ValueScoreRepository extends CrudRepository<ValueScore, Long> {

}
