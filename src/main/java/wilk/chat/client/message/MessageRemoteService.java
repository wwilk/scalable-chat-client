package wilk.chat.client.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import wilk.chat.client.core.AbstractRemoteService;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by wilk.wojtek@gmail.com.
 */
@Service
public class MessageRemoteService extends AbstractRemoteService{

    @Autowired
    MessageMapper messageMapper;

    public static final String RESOURCE_URL = "/api/message";

    public List<Message> getMessages(){
        HttpHeaders headers = getDefaultHeaders();
        RequestEntity<Void> request = new RequestEntity<>(headers, HttpMethod.GET, prepareUrl(RESOURCE_URL));

        List<RemoteMessage> result = restTemplate.exchange(request, new ParameterizedTypeReference<List<RemoteMessage>>() {}).getBody();

        return result.stream().map(messageMapper::fromRemote).collect(Collectors.toList());
    }

    public Message createMessage(Message message){
        HttpHeaders headers = getDefaultHeaders();
        RemoteMessage remoteMessage = messageMapper.toRemote(message);

        RequestEntity<RemoteMessage> request = new RequestEntity<>(remoteMessage, headers, HttpMethod.POST, prepareUrl(RESOURCE_URL));
        RemoteMessage result = restTemplate.exchange(request, RemoteMessage.class).getBody();

        return messageMapper.fromRemote(result);
    }

}
