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

    public List<User> getAllUsers(String excludedUsername){
        return entityManager
                .createNativeQuery("SELECT * FROM USERS WHERE USERNAME != ?1", User.class)
                .setParameter(1, excludedUsername)
                .getResultList();
    }

    // not thread-safe!
    public void createUserIfNotExists(String username) {
        User user = entityManager.find(User.class, username);
        if(user == null) {
            entityManager.persist(new User(username));
        }
    }
}
