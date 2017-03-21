package com.yberdaliyev.services;

import com.yberdaliyev.models.pojos.MyUserDetails;
import com.yberdaliyev.models.pojos.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Yerlan on 16.03.2017.
 */
@Service
public class MyUserDetailsService implements UserDetailsService {
    private IUserService userService;

    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUserDetails userDetails = userService.getUserDetailsByLogin(username);

        if (userDetails==null) throw new UsernameNotFoundException(username);

        List<SimpleGrantedAuthority> authorityList = new LinkedList<>();
        authorityList.add(new SimpleGrantedAuthority(userDetails.getRole().toString()));

        return new org.springframework.security.core.userdetails.User(username,userDetails.getPwd(),
                userDetails.getEnabled(),true,
                true,true, authorityList);
    }
}
