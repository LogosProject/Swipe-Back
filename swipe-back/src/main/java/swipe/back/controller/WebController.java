package swipe.back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import swipe.back.dao.ProblemRepository;
import swipe.back.dao.UserRepository;
import swipe.back.domain.Problem;
import swipe.back.domain.User;

//@EnableAutoConfiguration
@Controller
public class WebController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ProblemRepository problemRepository;
	
	@RequestMapping("/test")
	@ResponseBody
    String test() {
		User u = new User(123, "toto","toto");
		u.setPassword("password");
		u.setUsername("toto");
		userRepository.save(u);
		//problemRepository.save(new Problem(123, "toto", "toto"));
        return "Hello World!";
    }
}
