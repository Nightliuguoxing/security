package com.example.security.dao;

import com.example.security.entity.SUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022-03-08 20:14
 * @Description:
 */
@Repository
public interface UserDao extends BaseDao<SUser, String> {

    @Query(value = "SELECT u FROM SUser u WHERE u.username = ?1")
    public SUser findUserByName(String username);
}
