package com.osama.service;

import com.osama.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User saveUser(User user);
}
