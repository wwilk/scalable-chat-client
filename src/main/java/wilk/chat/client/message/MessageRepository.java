package wilk.chat.client.message;


import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by wilk.wojtek@gmail.com.
 */
@Repository
public class MessageRepository {

    @PersistenceContext
    EntityManager em;

    public void create(Message entity){
        em.persist(entity);
    }

    public List<Message> findAllForContact(String contact) {
        return em
                    .createNativeQuery("SELECT * FROM MESSAGE WHERE SENDER_ID = ?1 OR RECEIVER_ID = ?1", Message.class)
                    .setParameter(1, contact)
                    .getResultList();
    }
}
