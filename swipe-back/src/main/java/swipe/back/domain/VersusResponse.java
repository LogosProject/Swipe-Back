package swipe.back.domain;

import java.util.Collection;

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
public class VersusResponse {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private double response;
	@OneToOne
	private Versus versus;
	@OneToOne
	private User user;
	
	public VersusResponse(){
		
	}

	public VersusResponse(long id, double response, Versus versus, User user) {
		super();
		this.id = id;
		this.response = response;
		this.versus = versus;
		this.user = user;
	}
	

}
