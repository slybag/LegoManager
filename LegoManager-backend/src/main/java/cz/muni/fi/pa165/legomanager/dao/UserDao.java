/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.legomanager.dao;

import cz.muni.fi.pa165.legomanager.entity.LegoSet;
import cz.muni.fi.pa165.legomanager.entity.User;
import java.util.List;

/**
 *
 * @author Petr
 */
public interface UserDao {
    public List<User> getAllUsers();
    
    /**
     * Update lego set
     * @param legoSet to be updated
     */
    public void updateUser(User user);
    
    /**
     * Remove lego set
     * @param legoSet to be deleted
     * @throws IllegalArgumentException
     */
    public void deleteUser(User user);
    
    /**
     * Add new lego set
     * @param legoSet to be added   
     */
    public void addUser(User user);
    
    /**
     * Find lego set by id
     * @param id of wanted lego set
     * @return lego set with specified id
     */
    public User findUserById (Long id);
    
    public User findUserByAccountName (String accountName);
}
