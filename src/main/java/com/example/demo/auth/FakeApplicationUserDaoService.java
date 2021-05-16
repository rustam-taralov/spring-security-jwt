package com.example.demo.auth;

import com.google.common.collect.Lists;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.demo.security.ApplicationUserRole.*;

@Repository("fake")
public class FakeApplicationUserDaoService implements ApplicationUseDao{

    private final PasswordEncoder passwordEncoder;

    public FakeApplicationUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectUserByUserName(String username) {
        return getApplicationUsers().stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers(){
        List<ApplicationUser> applicationUsers= Lists.newArrayList(
                new ApplicationUser(
                        (List<? extends GrantedAuthority>) STUDENT.getGrantedAuthorities(),
                        passwordEncoder.encode("1234"),
                        "anna",
                        true,
                            true,
                        true,
                        true
                ),
                new ApplicationUser(
                        (List<? extends GrantedAuthority>) ADMIN.getGrantedAuthorities(),
                        passwordEncoder.encode("1234"),
                        "linda",
                        true,
                        true,
                        true,
                        true
                ),
                new ApplicationUser(
                        (List<? extends GrantedAuthority>) ADMINTRAINEE.getGrantedAuthorities(),
                        passwordEncoder.encode("1234"),
                        "tom",
                        true,
                        true,
                        true,
                        true
                )
        );
        return applicationUsers;
    }


}
