package swipe.back.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import swipe.back.domain.Solution;
import swipe.back.domain.User;
import swipe.back.domain.Value;
import swipe.back.domain.ValueSolutionScore;

@Repository
public interface ValueSolutionScoreRepository extends CrudRepository<ValueSolutionScore, Long> {

	ValueSolutionScore findByUserAndValueAndSolution(User user, Value value,
			Solution solution);

}
