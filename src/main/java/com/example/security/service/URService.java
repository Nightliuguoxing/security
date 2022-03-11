package com.example.security.service;

import com.example.security.dao.URDao;
import com.example.security.entity.UR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022-03-08 20:28
 * @Description:
 */
@Service
public class URService {

    @Autowired
    private URDao urDao;

    public List<UR> findAllByUserId(String uid){
        return urDao.findAllByUserId(uid);
    }

}
