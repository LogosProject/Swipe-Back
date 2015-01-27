package swipe.back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import swipe.back.dao.ProblemRepository;
import swipe.back.domain.Problem;

@Service
public class ProblemService implements IProblemService {

	
	@Autowired
	ProblemRepository problemRepository;
	
	@Override
	public Iterable<Problem> getAllProblems() {
		return this.problemRepository.findAll();
	}

	@Override
	public boolean createProblem(String name, String description) {
		Problem newProblem = new Problem ();
		newProblem.setDescription(description);
		newProblem.setName(name);
		try{
			this.problemRepository.save(newProblem);
			return true;
		}
		catch( RuntimeException e){
			return false;
		}
	}

}

