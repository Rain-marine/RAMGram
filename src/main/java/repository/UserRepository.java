package repository;

import models.Tweet;
import models.User;
import repository.utils.EntityManagerProvider;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class UserRepository {

    public User getById(Long userId){
        EntityManager em = EntityManagerProvider.getEntityManager();

        try {
            return em.find(User.class, userId);
        } catch (Exception e) {
            return null;
        }
    }

    public void insert(User user){
        EntityManager em = EntityManagerProvider.getEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
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
        EntityManager em = EntityManagerProvider.getEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
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
        EntityManager em = EntityManagerProvider.getEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
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

    public void mute(long userId, long mutedId) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            User user = em.find(User.class, userId);
            User mutedUser = em.find(User.class, mutedId);
            user.getMutedUsers().add(mutedUser);
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

    public void unmute(long userId, long mutedId) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            User user = em.find(User.class, userId);
            user.setMutedUsers(user.getMutedUsers().stream().filter(it -> it.getId() != mutedId).collect(Collectors.toList()));
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

    public void changeAccountVisibility(long id, boolean newVisibility) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
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
        EntityManager em = EntityManagerProvider.getEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
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
        EntityManager em = EntityManagerProvider.getEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
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
        EntityManager em = EntityManagerProvider.getEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
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
        EntityManager em = EntityManagerProvider.getEntityManager();

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
        EntityManager em = EntityManagerProvider.getEntityManager();

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
        EntityManager em = EntityManagerProvider.getEntityManager();

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

    public void addRetweet(long tweetId, long userId) {
        //add tweet to user's retweet list
        EntityManager em = EntityManagerProvider.getEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            User user = em.find(User.class, userId);
            Tweet tweet = em.find(Tweet.class, tweetId);
            user.getRetweetTweets().add(tweet);
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

    public void addFavoriteTweet(long userId, long tweetId) {
        //add tweet to user's favorite tweets
        EntityManager em = EntityManagerProvider.getEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            User user = em.find(User.class, userId);
            Tweet tweet = em.find(Tweet.class, tweetId);
            user.getFavoriteTweets().add(tweet);
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

    public void addReportedTweet(long tweetId, long userId){
        EntityManager em = EntityManagerProvider.getEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            User user = em.find(User.class, userId);
            Tweet tweet = em.find(Tweet.class, tweetId);
            user.getReportedTweets().add(tweet);
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

    public void increaseReportCount(long userId) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            User user = em.find(User.class, userId);
            user.setReportedCount(user.getReportedCount() + 1);
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

    public void changeNumberStatus(long id, User.Level status) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            User user = em.find(User.class, id);
            user.setPhoneNumberVisible(status);
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

    public void changeEmailStatus(long id, User.Level status) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            User user = em.find(User.class, id);
            user.setEmailVisible(status);
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

    public void changeBirthdayStatus(long id, User.Level status) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            User user = em.find(User.class, id);
            user.setBirthDayVisible(status);
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

    public void changeUsername(long userId, String newUsername) {

    }

    public void changeBio(long userId, String newBio) {
    }

    public void changeFullName(long userId, String newName) {
    }

    public void changeBirthdayDate(long userId, Date birthday) {
    }

    public void changeEmail(long userId, String newEmail) {

    }

    public void changePhoneNumber(long userId, String newNumber) {
    }


    public void unblock(long loggedUserId, long blockedUser) {
        //remove blockedUser from logged user blacklist
    }
}
