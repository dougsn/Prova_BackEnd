package Clinica_Odontologica.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AppUserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public AppUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User(username, userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(("User not found Exception"))).getPassword(), new ArrayList<>());
        //return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(("User not found Exception")));
    }
}
