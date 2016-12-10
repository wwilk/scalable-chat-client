package wilk.chat.client.message;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.TestAnnotationUtils;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.annotation.Transactional;
import wilk.chat.client.message.Message;
import wilk.chat.client.message.MessageService;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * Created by wilk.wojtek@gmail.com.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MessageServiceTest {

    @Autowired
    MessageService messageService;
    @Autowired
    EntityManager entityManager;

    @Mock
    MessageRemoteService messageRemoteService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(messageService, "messageRemoteService", messageRemoteService);
    }

    @Test
    public void should_create_messages(){
        Message message = new Message();
        message.setContent("content");
        message.setCreated(new Date());
        message.setSenderId("senderId");
        message.setReceiverId("receiverId");

        when(messageRemoteService.createMessage(message)).thenReturn(message);

        Message result = messageService.create(message);

        Message persisted = entityManager.find(Message.class, result.getId());
        assertThat(persisted.getCreated()).isEqualTo(message.getCreated());
        assertThat(persisted.getContent()).isEqualTo(message.getContent());
        assertThat(persisted.getSenderId()).isEqualToIgnoringCase(message.getSenderId());
        assertThat(persisted.getReceiverId()).isEqualTo(message.getReceiverId());
    }

    @Test
    public void should_find_all_messages(){
        Message in = new Message();
        in.setContent("content");
        in.setCreated(new Date());
        in.setSenderId("flip");
        in.setReceiverId("flap");

        Message out = new Message();
        out.setContent("content");
        out.setCreated(new Date());
        out.setReceiverId("flip");
        out.setSenderId("flap");

        entityManager.persist(in);
        entityManager.persist(out);

        List<Message> all = messageService.findAllForContact("flip");

        assertThat(all).contains(in, out);
    }
}