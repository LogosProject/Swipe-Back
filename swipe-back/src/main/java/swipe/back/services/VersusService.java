package swipe.back.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import swipe.back.dao.VersusRepository;
import swipe.back.dao.VersusResponseRepository;
import swipe.back.domain.Problem;
import swipe.back.domain.Solution;
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
		//3. en choisir un element de 1 qui n'est pas dans 2 et le renvoyer
		Collection<Versus> allVersuses = (Collection<Versus>) this.versusRepository.findByProblem(problem);
		Collection<VersusResponse> versusResponses = (Collection<VersusResponse>) this.versusResposeRepository.findForUserAndProblem(user, problem);
		Collection<Versus> respondedVersuses = new ArrayList<Versus>();
		for ( VersusResponse versusResponse : versusResponses){
			respondedVersuses.add(versusResponse.getVersus());
		}
		allVersuses.removeAll(respondedVersuses);
		Iterator<Versus> versusIterator = allVersuses.iterator();
		if ( versusIterator.hasNext()){
			return versusIterator.next();
		}
		else{
			return null;
		}
		
	}

	@Override
	public void createMissingVersuses(Problem problem, List<Solution> solutions) {
		Iterable<Versus> versuses = this.versusRepository.findByProblem(problem);
		List<Versus> newVersuses = new ArrayList<Versus> ();
		HashSet<Set<Solution>> neededMatches = new HashSet<Set<Solution>>();
		
		for ( Solution sol1 : solutions ){
			for ( Solution sol2 : solutions ){
				if ( ! sol1.equals(sol2)){
					//création d'un set de paires de solutions requises pour créer tous les versus
					HashSet<Solution> solutionPair = new HashSet<Solution>();
					solutionPair.add(sol1);
					solutionPair.add(sol2);
					if ( ! neededMatches.contains(solutionPair)){
						neededMatches.add(solutionPair);
					}
				}
			}
		}
		// Lister les paires de solutions déja couvertes par les vs existants
		HashSet<Set<Solution>> existingMatches = new HashSet<Set<Solution>> ();
		for ( Versus existingVersus : versuses ){
			HashSet<Solution> solutionPair = new HashSet<Solution>();
			solutionPair.add(existingVersus.getSolution1());
			solutionPair.add(existingVersus.getSolution2());
			existingMatches.add(solutionPair);
		}
		neededMatches.removeAll(existingMatches);
		for ( Set<Solution> solutionPair : neededMatches ){
			Versus newVersus = new Versus();
			newVersus.setProblem(problem);
			Iterator<Solution> newSolutions = solutionPair.iterator();
			newVersus.setSolution1(newSolutions.next());
			newVersus.setSolution2(newSolutions.next());
			newVersuses.add(newVersus);
		}
		this.versusRepository.save(newVersuses);
	}

}
