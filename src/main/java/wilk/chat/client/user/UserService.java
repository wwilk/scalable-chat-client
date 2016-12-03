package wilk.chat.client.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    @Autowired
    UserRemoteService userRemoteService;
    @Value("${auth.username}")
    String authenticatedUsername;

    public List<String> getAllAvailableContactsExceptForAuthenticatedUser(){
        return userRemoteService.getAvailableContacts()
                .stream()
                .filter(username -> !username.equals(authenticatedUsername))
                .collect(Collectors.toList());
    }

    public void createUserIfNotExists(String username){
        userRepository.createUserIfNotExists(username);
    }

    public String getAuthenticatedUsername() {
        return authenticatedUsername;
    }
}
