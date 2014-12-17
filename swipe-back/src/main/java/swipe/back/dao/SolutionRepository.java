package swipe.back.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import swipe.back.domain.Solution;

@Repository
public interface SolutionRepository extends CrudRepository<Solution, Long> {

}
