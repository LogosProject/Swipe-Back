package swipe.back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import swipe.back.dao.ValueRepository;
import swipe.back.domain.Value;

@Service
public class ValueService implements IValueService {

	@Autowired 
	ValueRepository valueRepository;

	@Override
	public Value createValue(String name, String description) {
		Value value = new Value();
		value.setName(name);
		value.setDescription(description);
		this.valueRepository.save(value);
		return value;
	}
	
	
}
