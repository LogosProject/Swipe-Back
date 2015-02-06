package swipe.back.domain;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class Value {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String name;
	private String description;
	@ManyToOne
	private Problem problem;
	
	public Value(){
		
	}
	public Value(String name , String description, Problem problem){
		this.name = name;
		this.description = description;		
		this.problem = problem;
	}
}
