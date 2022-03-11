package com.example.security.service;

import com.example.security.dao.UserDao;
import com.example.security.entity.SUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022-03-08 20:23
 * @Description:
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public SUser findUserById(String id){
        return userDao.findById(id).orElse(null);
    }

    public SUser findUserByName(String username){
        return userDao.findUserByName(username);
    }

}
