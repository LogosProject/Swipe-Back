package swipe.back.services;

import java.util.Collection;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import swipe.back.dao.CommentRepository;
import swipe.back.domain.Comment;
import swipe.back.domain.User;
import swipe.back.domain.Versus;

@Service
public class CommentService implements ICommentService {

	@Autowired
	CommentRepository commentRepository;
	
	@Override
	public Collection<Comment> getCommentsForVersus(Versus versus) {
		return this.commentRepository.findByVersus(versus);
	}

	@Override
	public Comment createComment(String name, String content, Versus versus, User user) {
		Comment comment = new Comment();
		comment.setContent(content);
		comment.setName(name);
		comment.setVersus(versus);
		comment.setUser(user);
		comment.setDateTime(new DateTime());
		this.commentRepository.save(comment);
		return comment;
	}

	@Override
	public Comment respondToComment(String name, String content,
			long commentId, User user) {
		Comment response = new Comment();
		Comment original = this.commentRepository.findOne(commentId);
		response.setComment(original);
		response.setName(name);
		response.setUser(user);
		response.setVersus(original.getVersus());
		response.setContent(content);
		this.commentRepository.save(response);
		return response;
	}

}
