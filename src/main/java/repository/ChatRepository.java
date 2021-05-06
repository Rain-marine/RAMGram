package repository;

import models.Chat;
import models.Message;
import models.User;
import repository.utils.EntityManagerProvider;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
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

    public void addMessageToChat(long chatId, Message message) {
        //add message to chat
        //set hasSeen to false
        //UnseenCount += 1

        EntityManager em = EntityManagerProvider.getEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            Chat chat = em.find(Chat.class, chatId);
            chat.setUnseenCount(chat.getUnseenCount() + 1);
            chat.setHasSeen(false);
            chat.getMessages().add(message);
            em.persist(chat);
            et.commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            if (et != null) {
                et.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void insert(Chat chat) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            em.persist(chat);
            et.commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            if (et != null) {
                et.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void clearUnSeenCount(long chatId) {
        //set unseen count to 0
        // set hasSeen to true

        EntityManager em = EntityManagerProvider.getEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            Chat chat = em.find(Chat.class, chatId);
            chat.setUnseenCount(0);
            chat.setHasSeen(true);
            em.persist(chat);
            et.commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            if (et != null) {
                et.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public Chat getById(long chatId) {
        EntityManager em = EntityManagerProvider.getEntityManager();

        try {
            return em.find(Chat.class, chatId);
        } catch (Exception e) {
            return null;
        }
    }
}
