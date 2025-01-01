package com.authentication.auth.Service;

import com.authentication.auth.Model.UsersModel;

public interface UserService {
    UsersModel register(UsersModel user);
    UsersModel login(String email, String password);
    void logout(String token);
}
