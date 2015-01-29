package swipe.back.services;

import swipe.back.domain.User;
import swipe.back.domain.Versus;
import swipe.back.domain.VersusResponse;

public interface IVersusResponseService {

	public VersusResponse createVersusResponse(double response, Versus versus, User user);
}
