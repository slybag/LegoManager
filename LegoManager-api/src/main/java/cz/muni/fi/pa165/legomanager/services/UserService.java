/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.legomanager.services;

import cz.muni.fi.pa165.legomanager.transferobjects.UserTO;
import java.util.List;

/**
 *
 * @author xhruso
 */
public interface UserService {
       
     /**
     * Finds all users
     * @return list of all users
     */
    public List<UserTO> getAllUsers() ;
    
    /**
     * Update user
     * @param user
     */
    public void updateUser(UserTO user);
    
    /**
     * Removes user
     * @param user to be deleted
     * @throws IllegalArgumentException
     */     
    public void deleteUser (UserTO user);
    
    /**
     * Add new user
     * @param user to be added   
     */
    public void createUser (UserTO user);
    
    /**
     * Find user by id
     * @param id of wanted user
     * @return user with specified id
     */
    public UserTO getUser (Long id);
}
