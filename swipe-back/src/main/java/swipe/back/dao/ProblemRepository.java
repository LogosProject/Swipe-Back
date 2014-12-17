package swipe.back.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import swipe.back.domain.Problem;

@Repository
public interface ProblemRepository extends CrudRepository<Problem, Long> {

}
