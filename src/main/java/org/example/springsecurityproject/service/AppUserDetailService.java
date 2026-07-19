package org.example.springsecurityproject.service;

import org.example.springsecurityproject.entity.AppUser;
import org.example.springsecurityproject.repository.AppUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailService implements UserDetailsService {

    private final AppUserRepository appUserRepository;

    public AppUserDetailService(AppUserRepository appUserRepository){
        this.appUserRepository=appUserRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser= appUserRepository.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("user with username"+username+"not found"));
        return User.builder().username(appUser.getUsername())
                .password(appUser.getPassword())
//                .roles(appUser.getRole())
                .authorities(getAuthorities(appUser.getRole()))
                .build();
    }

    private String[] getAuthorities(String role){
        if("ADMIN".equals(role)){
            return new String[]{"ROLE_ADMIN","BLOG_WRITE"};

        }

        return new String[]{"ROLE_USER"};
    }


}
