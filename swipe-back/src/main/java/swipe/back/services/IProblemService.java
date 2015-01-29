package swipe.back.services;

import swipe.back.domain.Problem;
import swipe.back.domain.Value;

public interface IProblemService {

	public Iterable<Problem> getAllProblems();

	public boolean createProblem(String name, String description);
	
	public Iterable<Value> getValuesForProblem(long problemId);
	
	public Value AddValue (long problemId,Value value);

}

