package com.example.demo.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService implements UserDetailsService {

    private final ApplicationUseDao applicationUseDao;

    @Autowired
    public ApplicationUserService(@Qualifier("fake") ApplicationUseDao applicationUseDao) {
        this.applicationUseDao = applicationUseDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return applicationUseDao
                .selectUserByUserName(username)
                .orElseThrow(()->
                        new UsernameNotFoundException(String.format("Username %s not founded",username)));
    }
}
