package swipe.back.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import swipe.back.domain.SolutionScore;

@Repository
public interface SolutionScoreRepository extends CrudRepository<SolutionScore, Long> {

}
