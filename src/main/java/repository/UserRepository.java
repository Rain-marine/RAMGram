package repository;

import models.User;
import repository.utils.EntityManagerProvider;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;

public class UserRepository {
    private final EntityManager em = EntityManagerProvider.getEntityManager();
    private final EntityTransaction et = em.getTransaction();

    public User getById(Long userId){
        try {
            return em.find(User.class, userId);
        } catch (Exception e) {
            return null;
        }
    }

    public void insert(User user){
        try{
            et.begin();
            em.persist(user);
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

    public void setLastSeen(long id, Date now) {
        try {
            et.begin();
            User user = em.find(User.class, id);
            user.setLastSeen(now);
            em.persist(user);
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

    public void deleteAccount(long id) {
        try {
            et.begin();
            User object = em.find(User.class, id);
            em.remove(object);
            et.commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            if (et != null) {
                et.rollback();
            }
            e.printStackTrace();
        }
    }

    public boolean doesPasswordExist(long id, String password) {
        return false;
    }

    public void changeAccountVisibility(long id, boolean newVisibility) {
        try {
            et.begin();
            User user = em.find(User.class, id);
            user.setPublic(newVisibility);
            em.persist(user);
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

    public void deactivateAccount(long id) {
        try {
            et.begin();
            User user = em.find(User.class, id);
            user.setActive(false);
            em.persist(user);
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

    public void changeLastSeenStatus(long id, String newStatus) {
        try {
            et.begin();
            User user = em.find(User.class, id);
            user.setLastSeenStatus(newStatus);
            em.persist(user);
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

    public void changePassword(long id, String newPassword) {
        try {
            et.begin();
            User user = em.find(User.class, id);
            user.setPassword(newPassword);
            em.persist(user);
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

    public User getByUsername(String username) {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<User> cq = cb.createQuery(User.class);
            Root<User> root = cq.from(User.class);

            cq.select(root).where(cb.equal(root.get("username"), username));
            TypedQuery<User> typedQuery = em.createQuery(cq);

            return typedQuery.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public User getByEmail(String email) {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<User> cq = cb.createQuery(User.class);
            Root<User> root = cq.from(User.class);

            cq.select(root).where(cb.equal(root.get("email"), email));
            TypedQuery<User> typedQuery = em.createQuery(cq);

            return typedQuery.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public User getByPhoneNumber(String phoneNumber) {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<User> cq = cb.createQuery(User.class);
            Root<User> root = cq.from(User.class);

            cq.select(root).where(cb.equal(root.get("phoneNumber"), phoneNumber));
            TypedQuery<User> typedQuery = em.createQuery(cq);

            return typedQuery.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
