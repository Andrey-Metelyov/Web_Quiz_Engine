package engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> getUserByEmail(String email) {
        System.out.println("Get user by email: " + email);
        return userRepository.getByEmail(email);
    }

    @Override
    public User saveUser(User user) {
        System.out.println("Save user: " + user);
        return userRepository.save(user);
    }
}
