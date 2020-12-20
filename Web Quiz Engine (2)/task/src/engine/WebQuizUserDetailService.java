package engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WebQuizUserDetailService implements UserDetailsService {
    @Autowired UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.getByEmail(username);
        System.out.println("Get user: " + username + " found: " + user.get());
        return new WebQuizUserDetails(user.get());
    }
}
