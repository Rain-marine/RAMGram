package repository;

import models.Chat;
import models.Group;
import models.User;
import repository.utils.EntityManagerProvider;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class FactionRepository {
    public void insert(Group newGroup) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            em.persist(newGroup);
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

    public void addUserToBlackList(long loggedUserId, long userToBlockId) {
        //add userToBlockId to loggedUser black list.
        EntityManager em = EntityManagerProvider.getEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            User user = em.find(User.class, loggedUserId);
            User userToBlock = em.find(User.class, userToBlockId);
            user.getBlackList().add(userToBlock);
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

    public void removeUserFromGroup(long removedUserId, int groupId) {
        //remove removedUserId from group with groupId ID.
        EntityManager em = EntityManagerProvider.getEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            User user = em.find(User.class, removedUserId);
            Group group = em.find(Group.class, groupId);
            group.getMembers().remove(user);
            em.persist(group);
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

    public Group getFactionById(int id) {
        EntityManager em = EntityManagerProvider.getEntityManager();

        try {
            return em.find(Group.class, id);
        } catch (Exception e) {
            return null;
        }
    }

    public void deleteFaction(int id) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            Group object = em.find(Group.class, id);
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

    public void deleteUserFromFaction(int groupId, long userId) {

    }

    public void addUserToFaction(int groupId, long userId) {

    }
}
