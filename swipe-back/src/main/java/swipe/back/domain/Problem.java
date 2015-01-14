package swipe.back.domain;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;


@Data
@Entity
public class Problem {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String name;
	private String description;
	@OneToMany
	private Collection<Value> values;
	public Problem(){
		
	}

	public Problem(long id, String name, String description, Collection<Value> values) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.values = values;
	}
	
	
}
