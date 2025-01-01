package com.authentication.auth.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.authentication.auth.Model.UsersModel;
import com.authentication.auth.Repository.UserRepository;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository,  BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public UsersModel register(UsersModel user){
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new RuntimeException("Email already exists");
        }
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username is already in use.");
        }
        user.setPassword((bCryptPasswordEncoder.encode(user.getPassword())));
        return userRepository.save(user);
    }



    @Override
    public UsersModel login(String email,String password){
        UsersModel user = userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("User not found"));
        if(bCryptPasswordEncoder.matches(password,user.getPassword())){
            return user;
        }
        throw new RuntimeException("Invalid password");

    }

    @Override
    public void logout(String token){
        System.out.println("User logged out");

    }
}
