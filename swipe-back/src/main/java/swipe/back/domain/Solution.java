package swipe.back.domain;

import javax.persistence.Column;
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
public class Solution {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String name;
	private String description;
	@ManyToOne
	private Problem problem;
	
	public Solution(){
		
	}

	public Solution(long id, String name, String description, Problem problem) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.problem = problem;
	}
	
	
}
