package com.example.security.security.service;

import com.example.security.entity.SRole;
import com.example.security.entity.SUser;
import com.example.security.entity.UR;
import com.example.security.service.RoleService;
import com.example.security.service.URService;
import com.example.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022-03-08 20:38
 * @Description:
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private URService urService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        // 获取用户信息
        SUser u = userService.findUserByName(username);

        // 判断用户是否存在
        if(u == null){
            throw new UsernameNotFoundException("用户名不存在");
        }

        // 添加权限
        List<UR> urList = urService.findAllByUserId(u.getId());
        for(UR ur : urList){
            SRole role = roleService.findRoleById(ur.getRoleId());
            authorities.add(new SimpleGrantedAuthority(role.getRolename()));
        }

        // 返回 UserDetails实现类
        return new User(u.getUsername(), u.getPassword(), authorities);
    }
}
