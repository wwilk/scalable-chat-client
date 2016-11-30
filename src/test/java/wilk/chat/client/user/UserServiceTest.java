package wilk.chat.client.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import wilk.chat.client.message.MessageService;

import javax.persistence.EntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by wilk.wojtek@gmail.com.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class UserServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    EntityManager entityManager;

    @Test
    public void should_find_all_users(){
        entityManager.persist(new User("testUser"));
        entityManager.persist(new User("testUser2"));

        List<String> allUsers = userService.getAllRecipientsIds();

        assertThat(allUsers).containsOnly("testUser", "testUser2");
    }
}