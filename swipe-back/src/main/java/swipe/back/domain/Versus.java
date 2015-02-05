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
public class Versus {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@OneToOne
	private Problem problem;
	@OneToOne
	private Solution solution1;
	@OneToOne
	private Solution solution2;
	@OneToOne
	private Value value;
	
	public Versus() {
		
	}

	public Versus(long id, Problem problem, Solution solution1,
			Solution solution2, Value value) {
		super();
		this.id = id;
		this.problem = problem;
		this.solution1 = solution1;
		this.solution2 = solution2;
		this.value = value;
	}
	
	
}
