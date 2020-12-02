package com.example.demo.web.dao;

import com.example.demo.web.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager(){
        return entityManager;
    }


    @Override
    public void newUser(User user) {
        entityManager.persist(user);
    }


    @Override
    public User getUserById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void deleteUser(long id) {
        getUserById(id).getEmail();
        User deletedUser = getUserById(id);
        entityManager.remove(deletedUser);
    }

    @Override
    public void editUser(User user) {
        entityManager.merge(user);
    }

    @Override
    @Transactional(readOnly = true)
    public long getId(String email) {
        long result;
        EntityManager entityManager = getEntityManager();
        List<?> tempResult =
                entityManager.createQuery("SELECT user from User user where user.email = ?1")
                        .setParameter(1, email)
                        .getResultList();
        if (tempResult.size() != 0) {
            result = ((User) tempResult.get(0)).getId();
        } else {
            result = -1;
        }
        return result;
    }
    @Override
    @SuppressWarnings("unchecked")
    public List<User> getListUsers() {
      return entityManager.createQuery("select user from User user").getResultList();}
}
