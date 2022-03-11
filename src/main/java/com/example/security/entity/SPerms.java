package com.example.security.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022-03-09 11:22
 * @Description:
 */
@Entity
@Table(name = "sys_permission")
public class SPerms implements Serializable {

    private static final long serialVersionUID = -2087587640449047600L;

    @Id
    private String id;

    private String url;

    private String roleId;

    private String permission;

    @Transient
    private List permissions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public List getPermissions() {
        return Arrays.asList(this.permission.trim().split(","));
    }

    public void setPermissions(List permissions) {
        this.permissions = permissions;
    }
}
