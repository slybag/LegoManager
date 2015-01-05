package cz.muni.fi.pa165.legomanager.dao;

import cz.muni.fi.pa165.legomanager.entity.User;
import java.util.List;

/**
 *
 * @author Petr
 */
public interface UserDao {
    public List<User> getAllUsers();
    
    /**
     * Update user
     * @param user to be updated
     */
    public void updateUser(User user);
    
    /**
     * Remove user
     * @param user to be deleted
     * @throws IllegalArgumentException
     */
    public void deleteUser(User user);
    
    /**
     * Add new user
     * @param user to be added   
     */
    public void addUser(User user);
    
    /**
     * Find user by id
     * @param id of wanted user
     * @return user with specified id
     */
    public User findUserById (Long id);
    
    /**
     * Find user by name
     * @param accountName
     * @return user with specified name
     */
    public User findUserByAccountName (String accountName);
}
