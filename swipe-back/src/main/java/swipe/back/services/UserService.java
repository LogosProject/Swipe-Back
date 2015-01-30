package swipe.back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import swipe.back.dao.UserRepository;
import swipe.back.domain.User;


@Service
public class UserService implements IUserService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public User getUserById(long id) {
		// TODO Auto-generated method stub
		User matchingUser = this.userRepository.findOne(id);
		return matchingUser;
	}

	@Override
	public User createUser(String email, String userName) {
		User userSameEmail = this.userRepository.findByEmail(email);
		if ( userSameEmail == null ){
			User newUser = new User(); //on l'aisse l'id s'autogénérer avec la méthode par défaut
			//TODO : remplacer l'autogénération par une méthode plus robuste pour éviter les collisions
			newUser.setEmail(email);
			newUser.setPassword("");
			newUser.setUsername(userName);
			try{
				this.userRepository.save(newUser);
			}
			catch(RuntimeException runtimeException){
				return null;
			}
			return newUser;
		}
		return null;
	}

}
