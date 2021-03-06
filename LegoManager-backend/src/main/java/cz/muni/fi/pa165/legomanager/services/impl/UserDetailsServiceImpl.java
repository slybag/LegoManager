/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.legomanager.services.impl;

import cz.muni.fi.pa165.legomanager.dao.UserDao;
import cz.muni.fi.pa165.legomanager.entity.User;
import cz.muni.fi.pa165.legomanager.services.UserService;
import cz.muni.fi.pa165.legomanager.transferobjects.UserTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Petr
 */
@Service("userAuthenticationProvider")
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService, UserService{

    @Autowired
    UserDao userDao;
    
    @Autowired
    DozerBeanMapper mapper;
    
    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        Object o = userDao.findUserByAccountName(string);
        if (o == null) {
            throw new UsernameNotFoundException("User not found {username= " + string + "}");
        }
        
        final UserTO userTO = mapper.map(o, UserTO.class);
        UserDetails details = new UserDetails() {

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                List<SimpleGrantedAuthority> auths = new ArrayList<>();
                auths.add(new SimpleGrantedAuthority(userTO.getRole()));
                return auths;
            }

            @Override
            public String getPassword() {
                return userTO.getPassword();
            }

            @Override
            public String getUsername() {
                return userTO.getAccountName();
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        };
        return details;
    }
    
    @Secured({"ROLE_ADMIN"})
    public void createUser(UserTO user) {        
        if(user==null) throw new IllegalArgumentException();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.addUser(mapper.map(user, User.class));       
    }
    
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setMapper(DozerBeanMapper mapper) {
        this.mapper = mapper;
    }
    
    @Override
    @Transactional
    public List<UserTO> getAllUsers() {
        List<UserTO> userTOs;
        List<User> users = userDao.getAllUsers();
        userTOs = new ArrayList<>();
        for (User user : users) {
            userTOs.add(mapper.map(user, UserTO.class));
        }
        return userTOs;
    }

    @Override
    @Transactional
    public void updateUser(UserTO user) {
        if(user==null) throw new IllegalArgumentException();
        String hash = getUser(user.getAccountName()).getPassword();
        if(!user.getPassword().equals(hash)){
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            user.setPassword(passwordEncoder.encode(user.getPassword()));    
        }
        userDao.updateUser(mapper.map(user, User.class));
    }

    @Override
    @Transactional
    public void deleteUser(UserTO user) {
        if(user==null) throw new IllegalArgumentException();
        userDao.deleteUser(mapper.map(user, User.class));
    }

    @Override
    @Transactional
    public UserTO getUser(String id) {
        if(id==null) throw new IllegalArgumentException();
        return mapper.map(userDao.findUserByAccountName(id), UserTO.class);
    }
}
