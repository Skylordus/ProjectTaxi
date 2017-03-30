package com.yberdaliyev.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by Yerlan on 30.03.2017.
 */
public interface MyUserDetailsService {
    public UserDetails loadUserByUsername(String username)  throws UsernameNotFoundException;
}
