package swipe.back.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class SolutionScore {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private double score;
	@OneToOne
	private Solution solution;
	@OneToOne
	private User user;
	
	public SolutionScore(){
		
	}

	public SolutionScore(long id, double score, Solution solution, User user) {
		super();
		this.id = id;
		this.score = score;
		this.solution = solution;
		this.user = user;
	}
	
	
}
