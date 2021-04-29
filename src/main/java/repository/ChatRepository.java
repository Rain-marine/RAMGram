package repository;

import models.Chat;
import models.Tweet;
import models.User;
import repository.utils.EntityManagerProvider;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

public class ChatRepository {
    public List<Chat> getAllChats(long userId) {
        //all userId chat orderBy unSeen message thenOrderBy Date(if is ok, it's not necessary)
        EntityManager em = EntityManagerProvider.getEntityManager();

        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Chat> cq = cb.createQuery(Chat.class);
            Root<Chat> root = cq.from(Chat.class);
            Join<Chat, User> chatUserJoin = root.join("users");

            cq.select(root);
            cq.where(cb.equal(chatUserJoin.get("id"), userId));

            cq.orderBy(cb.desc(root.get("hasSeen")));

            TypedQuery<Chat> typedQuery = em.createQuery(cq);

            return typedQuery.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}
