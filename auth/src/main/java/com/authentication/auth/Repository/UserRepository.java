package com.authentication.auth.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;

import com.authentication.auth.Model.UsersModel;;


public interface UserRepository extends JpaRepository<UsersModel, Long> {
    Optional<UsersModel> findByUsername(String username);
    Optional<UsersModel> findByEmail(String email);
}
