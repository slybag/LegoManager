package cz.muni.fi.pa165.legomanager.dao.impl;

import cz.muni.fi.pa165.legomanager.dao.UserDao;
import cz.muni.fi.pa165.legomanager.entity.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Petr
 */
@Repository
public class UserDaoImpl implements UserDao {
    
    @PersistenceContext
    EntityManager entityManager;
    
    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public void updateUser(User user) {
        if (user == null || !isValidUser(user)) throw new IllegalArgumentException();
        entityManager.merge(user);
    }

    @Override
    public void deleteUser(User user) {
        if (user == null) throw new IllegalArgumentException();
        
        User userToDelete = entityManager.merge(user);
        
        entityManager.remove(userToDelete);
    }

    @Override
    public void addUser(User user) {
        if ( user == null || !isValidUser(user)) throw new IllegalArgumentException();
        entityManager.persist(user);
    }

    @Override
    public User findUserById(Long id) {
        User userToFind = entityManager.find(User.class, id);
        if (userToFind == null) throw new PersistenceException("User with id: " + id + " is not in DB");
        
        return userToFind;
    }
    
    //prepared function
    private boolean isValidUser(User user) {                             
        if (user.getAccountName()== null || 
                user.getPassword()== null || 
                user.getRole()== null) return false;        
        return true;
    }
    
    @Override
    public User findUserByAccountName(String accountName) {
        User userToFind = entityManager.find(User.class, accountName);
        if (userToFind == null) throw new PersistenceException("User with account name: " + accountName + " is not in DB");
        return userToFind;
    }
}
