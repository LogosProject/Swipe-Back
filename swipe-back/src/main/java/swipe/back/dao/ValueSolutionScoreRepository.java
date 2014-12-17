package swipe.back.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import swipe.back.domain.ValueSolutionScore;

@Repository
public interface ValueSolutionScoreRepository extends CrudRepository<ValueSolutionScore, Long> {

}
