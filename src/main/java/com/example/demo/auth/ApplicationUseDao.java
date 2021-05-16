package com.example.demo.auth;

import java.util.Optional;

public interface ApplicationUseDao {

    Optional<ApplicationUser> selectUserByUserName(String username);
}
