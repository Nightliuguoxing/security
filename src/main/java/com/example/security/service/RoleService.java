package com.example.security.service;

import com.example.security.dao.RoleDao;
import com.example.security.entity.SRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022-03-08 20:26
 * @Description:
 */
@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    public SRole findRoleById(String id){
        return roleDao.findById(id).orElse(null);
    }

    public SRole findRoleByName(String name){
        return roleDao.findRoleByNme(name);
    }

}
