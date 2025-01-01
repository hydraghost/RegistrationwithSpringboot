package com.authentication.auth.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.authentication.auth.Model.UsersModel;
import com.authentication.auth.Service.UserService;


@RestController
@RequestMapping("/api/auth")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello World";
    }

    @GetMapping("/login")
    public String login(){
        return "Login";
    }

   @PostMapping("/login")
   public ResponseEntity<UsersModel> login(@RequestBody UsersModel user){
    try{
        UsersModel loggedInUser = userService.login(user.getEmail(), user.getPassword());
        // return ResponseEntity.ok(loggedInUser);
       return ResponseEntity.status(201).body(loggedInUser);
    }catch(Exception e){
        return ResponseEntity.status(401).body(null);
    }
       
   }

   @GetMapping("/register")
    public String register(){
         return "Register";
    }

   @PostMapping("/register")
   public ResponseEntity<UsersModel> register(@RequestBody UsersModel user){
         System.out.println(user);
         UsersModel registeredUser = userService.register(user);
         return ResponseEntity.status(201).body(registeredUser);
   }


   
   @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestParam String token){
         userService.logout(token);
         return ResponseEntity.status(200).body("User logged out");
    }
    
}
