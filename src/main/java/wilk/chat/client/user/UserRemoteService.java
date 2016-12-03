package wilk.chat.client.user;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import wilk.chat.client.core.AbstractRemoteService;

import java.util.List;

/**
 * Created by wilk.wojtek@gmail.com.
 */
@Service
public class UserRemoteService extends AbstractRemoteService {
    public List<String> getAvailableContacts(){
        HttpHeaders headers = getDefaultHeaders();
        RequestEntity request = new RequestEntity(headers, HttpMethod.GET, prepareUrl("/api/user"));
        return restTemplate.exchange(request, new ParameterizedTypeReference<List<String>>() {}).getBody();
    }
}
