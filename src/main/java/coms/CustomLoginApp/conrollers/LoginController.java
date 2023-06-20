package coms.CustomLoginApp.conrollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/welcome")
    public String greeting() {
        return "Welcome";
    }

    @GetMapping("/login")
    public String login(){
        return "Login";
    }
}
