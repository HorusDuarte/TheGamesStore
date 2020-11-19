package piiv.senac.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Login_Controller {
	
	@GetMapping("/login")
	public ModelAndView negado() {
		ModelAndView mv = new ModelAndView("/login");
		
		return mv;
	}

}
