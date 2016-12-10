package wilk.chat.client.message;

import org.springframework.stereotype.Service;

/**
 * Created by wilk.wojtek@gmail.com.
 */
@Service
public class MessageMapper {

    public Message fromRemote(RemoteMessage remote){
        Message message = new Message();
        message.setContent(remote.getBody());
        message.setCreated(remote.getCreated());
        message.setRemoteId(remote.getMessageId());
        message.setReceiverId(remote.getReceiverId());
        message.setSenderId(remote.getSenderId());
        return message;
    }

    public RemoteMessage toRemote(Message message){
        RemoteMessage remote = new RemoteMessage();
        remote.setBody(message.getContent());
        remote.setCreated(message.getCreated());
        remote.setMessageId(message.getRemoteId());
        remote.setReceiverId(message.getReceiverId());
        remote.setSenderId(message.getSenderId());
        return remote;
    }
}
