package com.example.security.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022-03-08 20:08
 * @Description:
 */
@Entity
@Table(name = "sys_role")
public class SRole implements Serializable {

    private static final long serialVersionUID = -5282948228767600222L;

    @Id
    private String id;

    private String rolename;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    @Override
    public String toString() {
        return "{\"id\": " + id + ",\"rolename\":" + rolename + "}";
    }
}
