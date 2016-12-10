package wilk.chat.client.message;

import java.util.Date;

/**
 * Created by wilk.wojtek@gmail.com.
 */
public class RemoteMessage {
    private String messageId;
    private String receiverId;
    private String body;
    private String senderId;
    private Date created;

    public RemoteMessage(){
    }

    public RemoteMessage(String body, String senderId, String receiverId, Date created) {
        this.body = body;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.created = created;
    }

    public String getBody() {
        return body;
    }

    public String getSenderId() {
        return senderId;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }
}
