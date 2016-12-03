package wilk.chat.client.user;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.annotation.Transactional;
import wilk.chat.client.message.MessageService;

import javax.persistence.EntityManager;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

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
    @Mock
    UserRemoteService userRemoteService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(userService, "userRemoteService", userRemoteService);
    }

    @Test
    public void should_create_user(){
        userService.createUserIfNotExists("testUser");

        assertThat(entityManager.find(User.class, "testUser")).isNotNull();
    }

    @Test
    public void should_get_all_contacts_except_for_authenticated(){
        when(userRemoteService.getAvailableContacts()).thenReturn(Arrays.asList("first", "second", "third"));
        ReflectionTestUtils.setField(userService, "authenticatedUsername", "second");

        List<String> usernames = userService.getAllAvailableContactsExceptForAuthenticatedUser();

        assertThat(usernames).containsExactly("first", "third");
    }
}