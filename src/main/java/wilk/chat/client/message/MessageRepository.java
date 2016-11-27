package wilk.chat.client.message;


import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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

    public List<Message> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Message> cq = cb.createQuery(Message.class);
        Root<Message> rootEntry = cq.from(Message.class);
        CriteriaQuery<Message> all = cq.select(rootEntry);
        TypedQuery<Message> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }
}
