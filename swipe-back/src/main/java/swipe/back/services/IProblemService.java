package swipe.back.services;

import swipe.back.domain.Problem;

public interface IProblemService {

	public Iterable<Problem> getAllProblems();

	public boolean createProblem(String name, String description);

}

