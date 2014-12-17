package swipe.back.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import swipe.back.domain.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

}
