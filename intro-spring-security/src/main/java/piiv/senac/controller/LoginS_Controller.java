package piiv.senac.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginS_Controller {

    @GetMapping("/login-sucesso")
    public ModelAndView showView(){

        ModelAndView mv = new ModelAndView("login-sucesso");
        return mv;
    }

@GetMapping("/loss")
    public ModelAndView exibirHome(){

        return showView();
    }

}
