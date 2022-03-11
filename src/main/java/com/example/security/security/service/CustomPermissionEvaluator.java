package com.example.security.security.service;

import com.example.security.entity.SPerms;
import com.example.security.service.PermsService;
import com.example.security.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022-03-09 12:38
 * @Description:
 */
@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {

    @Autowired
    private PermsService permissionService;

    @Autowired
    private RoleService roleService;

    @Override
    public boolean hasPermission(Authentication authentication, Object targetUrl, Object targetPermission) {
        // 获得loadUserByUsername()方法的结果
        User user = (User)authentication.getPrincipal();
        // 获得loadUserByUsername()中注入的角色
        Collection<GrantedAuthority> authorities = user.getAuthorities();

        // 遍历用户所有角色
        for(GrantedAuthority authority : authorities) {
            String roleName = authority.getAuthority();
            String roleId = roleService.findRoleByName(roleName).getId();
            // 得到角色所有的权限
            List<SPerms> permissionList = permissionService.findPermsByRoleId(roleId);

            // 遍历permissionList
            for(SPerms p : permissionList) {
                // 获取权限集
                List permissions = p.getPermissions();
                // 如果访问的Url和权限用户符合的话，返回true
                if(targetUrl.equals(p.getUrl())
                        && permissions.contains(targetPermission)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        return false;
    }
}
