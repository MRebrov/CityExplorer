package ru.netcracker.registration.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.netcracker.registration.model.User;
import ru.netcracker.registration.repository.UserRepository;
import ru.netcracker.registration.security.model.SpringSecurityUser;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    private UserRepository repository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.findByEmail(email);

        if(user != null) {
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            grantedAuthorities.add(new SimpleGrantedAuthority(user.getGroupID().getName()));

            return new SpringSecurityUser(
                    user.getId(),
                    user.getEmail(),
                    user.getPassword(),
                    null,
                    grantedAuthorities
            );
        }
        else{
            throw new UsernameNotFoundException(String.format("No User found with username '%s'.", email));
        }
    }
}
