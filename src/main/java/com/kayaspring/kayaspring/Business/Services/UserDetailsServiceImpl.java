package com.kayaspring.kayaspring.Business.Services;

import com.kayaspring.kayaspring.Data.Repositories.IUserRepository;
import com.kayaspring.kayaspring.Entities.Models.User.AppUser;
import com.kayaspring.kayaspring.Entities.Models.User.UserDetailsImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final IUserRepository userRepository;

    public UserDetailsServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepository.findByUsername(username);

        return UserDetailsImpl.build(user);
    }

}