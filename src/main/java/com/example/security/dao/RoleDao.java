package com.example.security.dao;

import com.example.security.entity.SRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022-03-08 20:19
 * @Description:
 */
@Repository
public interface RoleDao extends BaseDao<SRole, String> {

    @Query(value = "SELECT r FROM SRole r WHERE r.rolename = ?1")
    public SRole findRoleByNme(String name);
}
