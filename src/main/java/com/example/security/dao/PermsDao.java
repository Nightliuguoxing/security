package com.example.security.dao;

import com.example.security.entity.SPerms;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022/3/9 12:20
 * @Description:
 */
@Repository
public interface PermsDao extends BaseDao<SPerms, String>{

    @Query(value ="SELECT p FROM SPerms p WHERE p.roleId = ?1")
    public List<SPerms> findPermsByRoleId(String roleId);
}
