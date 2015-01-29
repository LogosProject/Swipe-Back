package swipe.back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import swipe.back.dao.VersusResponseRepository;
import swipe.back.domain.User;
import swipe.back.domain.Versus;
import swipe.back.domain.VersusResponse;

@Service
public class VersusResponseService implements IVersusResponseService {

	@Autowired
	VersusResponseRepository versusResponseRepository;
	
	@Override
	public VersusResponse createVersusResponse(double response, Versus versus,
			User user) {
		VersusResponse vsr = new VersusResponse();
		vsr.setResponse(response);
		vsr.setUser(user);
		vsr.setVersus(versus);
		this.versusResponseRepository.save(vsr);
		return vsr;
	}

}
