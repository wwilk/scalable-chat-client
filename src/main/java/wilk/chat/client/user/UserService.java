package wilk.chat.client.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by wilk.wojtek@gmail.com.
 */
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<String> getAllRecipientsIds(String excludedUsername){
        return userRepository
                .getAllUsers(excludedUsername)
                .stream()
                .map(user -> user.getUsername())
                .collect(Collectors.toList());
    }

    public void createUserIfNotExists(String username){
        userRepository.createUserIfNotExists(username);
    }
}
