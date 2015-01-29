package swipe.back.dao;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import swipe.back.domain.Comment;
import swipe.back.domain.Versus;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

	public Collection<Comment> findByVersus(Versus versus);

}
