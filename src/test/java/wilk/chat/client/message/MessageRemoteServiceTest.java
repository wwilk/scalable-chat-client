package wilk.chat.client.message;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.remoting.RemoteTimeoutException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

/**
 * Created by wilk.wojtek@gmail.com.
 */
@RunWith(MockitoJUnitRunner.class)
public class MessageRemoteServiceTest {

    @InjectMocks
    MessageRemoteService service;

    @Mock
    RestTemplate template;
    @Mock
    MessageMapper messageMapper;

    @Test
    public void should_get_messages() throws Exception {
        RemoteMessage firstMessage = new RemoteMessage();
        RemoteMessage secondMessage = new RemoteMessage();

        List<RemoteMessage> remoteMessages = Arrays.asList(firstMessage, secondMessage);
        ResponseEntity response = new ResponseEntity(remoteMessages, HttpStatus.OK);
        when(template.exchange(any(RequestEntity.class), any(ParameterizedTypeReference.class)))
                .thenReturn(response);

        Message firstLocalMessage = new Message();
        Message secondLocalMessage = new Message();
        when(messageMapper.fromRemote(firstMessage)).thenReturn(firstLocalMessage);
        when(messageMapper.fromRemote(secondMessage)).thenReturn(secondLocalMessage);

        List<Message> result = service.getMessages();

        assertThat(result).containsExactly(firstLocalMessage, secondLocalMessage);
    }

    @Test
    public void should_create_message() throws Exception {
        Message localMessageSent = new Message();
        RemoteMessage remoteMessageSent = new RemoteMessage();
        RemoteMessage remoteMessageReceived = new RemoteMessage();
        Message localMessageReceived = new Message();

        when(messageMapper.toRemote(localMessageSent)).thenReturn(remoteMessageSent);
        ResponseEntity response = new ResponseEntity(remoteMessageReceived, HttpStatus.OK);
        when(template.exchange(any(), eq(RemoteMessage.class))).thenReturn(response);
        when(messageMapper.fromRemote(remoteMessageReceived)).thenReturn(localMessageReceived);

        Message result = service.createMessage(localMessageSent);

        assertThat(result).isSameAs(localMessageReceived);
    }
}