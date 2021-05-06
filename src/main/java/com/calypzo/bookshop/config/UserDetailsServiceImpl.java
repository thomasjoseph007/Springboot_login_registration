package com.calypzo.bookshop.config;

import com.calypzo.bookshop.model.User;
import com.calypzo.bookshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
      User user =userRepository.findByEmail(email);
        if(user==null)
            throw new UsernameNotFoundException("invalid");
        UserDetails userDetails = new CustomDetails(user);
        return userDetails;
    }
}
