package swipe.back.services;

import swipe.back.domain.Problem;
import swipe.back.domain.Value;

public interface IValueService {

	
	public Value createValue(String name, String description, Problem problem);
}
