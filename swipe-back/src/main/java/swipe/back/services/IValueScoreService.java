package swipe.back.services;

import swipe.back.domain.User;
import swipe.back.domain.Value;
import swipe.back.domain.ValueScore;

public interface IValueScoreService {

	public ValueScore createValueScore (double score, Value value, User user);
}
