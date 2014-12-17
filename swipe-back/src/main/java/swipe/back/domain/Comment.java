package swipe.back.domain;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.joda.time.DateTime;

import lombok.Data;

@Data
@Entity
public class Comment {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String title;
	private DateTime dateTime; 
	private String content;
	@OneToOne
	private Versus versus;
	@OneToOne
	private User user;
	@OneToOne
	private Comment comment;
	
	public Comment(){
		
	}

	public Comment(long id, String title, DateTime dateTime, String content,
			Versus versus, User user, Comment comment) {
		super();
		this.id = id;
		this.title = title;
		this.dateTime = dateTime;
		this.content = content;
		this.versus = versus;
		this.user = user;
		this.comment = comment;
	}
	
	
	
}
