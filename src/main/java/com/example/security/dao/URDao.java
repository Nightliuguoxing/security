package com.example.security.dao;

import com.example.security.entity.UR;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022/3/8 20:20
 * @Description:
 */
@Repository
public interface URDao extends BaseDao<UR, String>{

    @Query(value = "SELECT ur FROM UR ur WHERE ur.userId = ?1")
    public List<UR> findAllByUserId(String uid);
}
