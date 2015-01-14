package swipe.back.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import swipe.back.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	public User findByEmail(String email);

}
