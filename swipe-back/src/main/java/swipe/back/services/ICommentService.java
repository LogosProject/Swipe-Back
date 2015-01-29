package swipe.back.services;

import java.util.Collection;

import swipe.back.domain.Comment;
import swipe.back.domain.User;
import swipe.back.domain.Versus;

public interface ICommentService {
	public Collection<Comment> getCommentsForVersus(Versus versus);
	public Comment createComment ( String name, String description, Versus versus, User user);
	public Comment respondToComment (String name, String content, long commentId, User user);
}
