package swipe.back.services;

import swipe.back.domain.User;

public interface IUserService {

	/**
	 * Cherche un utilisateur par son id
	 * @param id
	 * @return l'utilisateur avec l'id correspondant, null si non trouvé
	 */
	public User getUserById(long id);
	
	/**
	 * Crée un nouvel utilisateur à partir de son adresse email
	 * @param email
	 * @return true si l'utilisateur est créé, false sinon
	 */
	public boolean createUser(String email);
}
