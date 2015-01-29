package swipe.back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import swipe.back.dao.ValueScoreRepository;
import swipe.back.domain.User;
import swipe.back.domain.Value;
import swipe.back.domain.ValueScore;

@Service
public class ValueScoreService implements IValueScoreService {
	
	@Autowired
	ValueScoreRepository valueScoreRepository;

	@Override
	public ValueScore createValueScore(double score, Value value, User user) {
		ValueScore valueScore = new ValueScore();
		valueScore.setScore(score);
		valueScore.setUser(user);
		valueScore.setValue(value);
		this.valueScoreRepository.save(valueScore);
		return valueScore;
	}

}
