package com.example.security.service;

import com.example.security.dao.PermsDao;
import com.example.security.entity.SPerms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022-03-09 12:30
 * @Description:
 */
@Service
public class PermsService {

    @Autowired
    private PermsDao permsDao;

    public List<SPerms> findPermsByRoleId(String roleId){
        return permsDao.findPermsByRoleId(roleId);
    }

}
