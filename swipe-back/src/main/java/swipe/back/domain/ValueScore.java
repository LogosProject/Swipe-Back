package swipe.back.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class ValueScore {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private double score;
	@ManyToOne
	private Value value;
	@ManyToOne
	private User user;
	
	public ValueScore ( ){
		
	}

	public ValueScore(long id, double score, Value value, User user) {
		super();
		this.id = id;
		this.score = score;
		this.value = value;
		this.user = user;
	}

	
}
