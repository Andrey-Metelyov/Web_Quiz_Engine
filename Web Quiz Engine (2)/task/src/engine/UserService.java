package engine;

import java.util.Optional;

public interface UserService {
    Optional<User> getUserByEmail(String email);

    User saveUser(User user);
}
