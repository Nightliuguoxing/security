package com.example.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022-03-08 20:13
 * @Description:
 */
@NoRepositoryBean
public interface BaseDao<T, I extends Serializable> extends JpaRepository<T, I>, JpaSpecificationExecutor<T> {

}
