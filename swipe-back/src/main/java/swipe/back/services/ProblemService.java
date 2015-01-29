package swipe.back.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import swipe.back.dao.ProblemRepository;
import swipe.back.domain.Problem;
import swipe.back.domain.Value;

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

	@Override
	public Iterable<Value> getValuesForProblem(long problemId) {
		Problem problem = this.problemRepository.findOne(problemId);
		return problem.getValues();
	}

	@Override
	public Value AddValue(long problemId, Value value) {
		Problem problem = this.problemRepository.findOne(problemId);
		Collection<Value>values = problem.getValues();
		values.add(value);
		problem.setValues(values);
		this.problemRepository.save(problem);
		return value;
	}



}

