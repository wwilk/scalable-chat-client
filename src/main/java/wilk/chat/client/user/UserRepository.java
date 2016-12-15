package wilk.chat.client.user;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by wilk.wojtek@gmail.com.
 */
@Repository
public class UserRepository {

    @PersistenceContext
    EntityManager entityManager;

    public List<User> getAllUsers(){
        return entityManager
                .createNativeQuery("SELECT * FROM USERS", User.class)
                .getResultList();
    }

    // not thread-safe!
    public void createUserIfNotExists(String username) {
        User user = entityManager.find(User.class, username);
        if(user == null) {
            user = new User();
            user.setUsername(username);
            entityManager.persist(user);
        }
    }
}