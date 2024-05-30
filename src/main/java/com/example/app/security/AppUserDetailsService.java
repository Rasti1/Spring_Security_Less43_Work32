package com.example.app.security;

import com.example.app.entity.Contact;
import com.example.app.repository.ContactRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class AppUserDetailsService implements UserDetailsService {

    private final ContactRepository contactRepository;

    public AppUserDetailsService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Contact contact = contactRepository.findByEmail(email);

        if (contact != null) {
            return new org.springframework.security.core.userdetails.User(
                    contact.getEmail(),
                    contact.getPassword(),
                    mapRolesToAuthorities()
            );
        } else {
            throw new UsernameNotFoundException("Invalid login or password.");
        }
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
    }
}
