package wilk.chat.client.message;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by wilk.wojtek@gmail.com.
 */
public class MessageMapperTest {

    MessageMapper mapper;

    @Before
    public void setUp(){
        mapper = new MessageMapper();
    }

    @Test
    public void should_convert_from_remote() throws Exception {
        RemoteMessage input = new RemoteMessage();
        input.setBody("body");
        input.setCreated(new Date());
        input.setMessageId("messageId");
        input.setReceiverId("receiverId");
        input.setSenderId("senderId");

        Message expected = new Message();
        expected.setContent(input.getBody());
        expected.setCreated(input.getCreated());
        expected.setReceiverId(input.getReceiverId());
        expected.setRemoteId(input.getMessageId());
        expected.setSenderId(input.getSenderId());

        Message result = mapper.fromRemote(input);

        assertThat(result).isEqualToComparingFieldByField(expected);
    }

    @Test
    public void should_convert_to_remote() throws Exception {
        Message input = new Message();
        input.setContent("body");
        input.setCreated(new Date());
        input.setRemoteId("messageId");
        input.setReceiverId("receiverId");
        input.setSenderId("senderId");

        RemoteMessage expected = new RemoteMessage();
        expected.setBody(input.getContent());
        expected.setCreated(input.getCreated());
        expected.setReceiverId(input.getReceiverId());
        expected.setMessageId(input.getRemoteId());
        expected.setSenderId(input.getSenderId());

        RemoteMessage result = mapper.toRemote(input);

        assertThat(result).isEqualToComparingFieldByField(expected);
    }
}