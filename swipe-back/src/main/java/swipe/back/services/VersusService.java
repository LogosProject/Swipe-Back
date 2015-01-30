package swipe.back.services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import swipe.back.dao.VersusRepository;
import swipe.back.dao.VersusResponseRepository;
import swipe.back.domain.Problem;
import swipe.back.domain.User;
import swipe.back.domain.Versus;
import swipe.back.domain.VersusResponse;

@Service
public class VersusService implements IVersusService {

	@Autowired
	VersusRepository versusRepository;
	
	@Autowired
	VersusResponseRepository versusResposeRepository;
	
	@Override
	public Versus getNextVersus(Problem problem, User user) {
		//1. récupérer tous les versus du probleme
		//2. recupérer tous ls versusresponse de l'utilisateurcorresponant à ces versus
		//3. en choisir un element de 1 qui n'est pas ans 2 et le renvoyer
		Collection<Versus> allVersuses = (Collection<Versus>) this.versusRepository.findByProblem(problem);
		Collection<VersusResponse> versusResponses = (Collection<VersusResponse>) this.versusResposeRepository.findForUserAndProblem(user, problem);
		Collection<Versus> respondedVersuses = new ArrayList<Versus>();
		for ( VersusResponse versusResponse : versusResponses){
			respondedVersuses.add(versusResponse.getVersus());
		}
		allVersuses.removeAll(respondedVersuses);
		Versus nextVersus = allVersuses.iterator().next();
		return nextVersus;
	}

}
