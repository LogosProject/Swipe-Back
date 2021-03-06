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
public class ValueSolutionScore {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private double score;
	@ManyToOne
	private Value value;
	@ManyToOne
	private User user;
	@ManyToOne
	private Solution solution;
	
	public ValueSolutionScore(){
		
	}

	public ValueSolutionScore(long id, double score, Value value, User user,
			Solution solution) {
		super();
		this.id = id;
		this.score = score;
		this.value = value;
		this.user = user;
		this.solution = solution;
	}
	
	
}
