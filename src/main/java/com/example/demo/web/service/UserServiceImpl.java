package com.example.demo.web.service;

import com.example.demo.web.dao.UserDao;
import com.example.demo.web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public void newUser(User user) {
        this.userDao.newUser(user);
    }

    @Override
    @Transactional
    public List<User> getListUsers() {
        return this.userDao.getListUsers();
    }

    @Override
    public User getUserById(long id) {
        return userDao.getUserById(id);
    }

    @Override
    @Transactional
    public void deleteUser(long id) {
        this.userDao.deleteUser(id);
    }

    @Override
    @Transactional
    public void editUser(User user) {
        this.userDao.editUser(user);
    }


    @Override
    @Transactional
    public long getId(String email) {
        return userDao.getId(email);
    }

    @Override
    public UserDetails loadUserByUsername(String user) throws UsernameNotFoundException {
        return userDao.getUserById(userDao.getId(user));
    }
}
