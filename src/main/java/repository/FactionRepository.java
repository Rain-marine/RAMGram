package repository;

import models.Group;
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
    }

    public void addUserToMutedList(long loggedUserId, long userToMutedId) {
        //add userToMutedId to loggedUser muted list.
    }

    public void removeUserFromGroup(long removedUserId, int groupId) {
        //remove removedUserId from group with groupId ID.
    }
}
