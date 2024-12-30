package com.authentication.auth.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ContentController {
   @GetMapping("/login")
   public String login(){
    return "login";
   }

   @GetMapping("/signup")
    public String signup(){
        return "signup";
    }
   
   
    
}
