/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.legomanager.dao;

import cz.muni.fi.pa165.legomanager.entity.Category;
import cz.muni.fi.pa165.legomanager.entity.LegoKit;
import cz.muni.fi.pa165.legomanager.entity.LegoSet;
import cz.muni.fi.pa165.legomanager.entity.User;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Petr Konecny
 */

public class UserDaoImplTest extends BaseDaoTest {
  
   @Autowired
   UserDao userDao;
   
   @PersistenceContext
   EntityManager em;
    
   
   //CREATE tests
       
    @Test
    public void testCreate(){
        
        User user = createUser("Honza", "password", "user");
        userDao.addUser(user);
        User userFromDb = em.find(User.class, user.getAccountName());
        assertNotNull (userFromDb);
        assertDeepEquals (userFromDb,user);
            
    }
       
    @Test(expected=DataAccessException.class)
    public void testCreateUserWithNullArgument(){
      
         userDao.addUser(null);
    }
    
    @Test(expected=DataAccessException.class)
    public void testCreateUserWithNullName(){
                
         User user = createUser(null, "password", "user");
         userDao.addUser(user);
        
    }
    
    @Test(expected=DataAccessException.class)
    public void testCreateUserWithNullPass(){

        User user = createUser("Honza", null, "user");
        userDao.addUser(user);
      
    }
    
    @Test(expected=DataAccessException.class)
    public void testCreateUserWithNullRole(){

        User user = createUser("Honza", "password", null);
        userDao.addUser(user);
      
    }
      
//    @Test
//    public void testUpdateUser(){
//        User user = createUser("Honza", "password", "user");
//        userDao.addUser(user);
//        user.setAccountName("Novak");
//        userDao.updateUser(user);
//        User userFromDatabase = em.find(User.class, user.getAccountName());
//        assertTrue(userFromDatabase.getAccountName().equals(user.getAccountName()));
//    }
//    
    @Test(expected=DataAccessException.class)
    public void testUpdateUserWithNull(){
        userDao.updateUser(null);
    }
    
    @Test(expected=DataAccessException.class)
    public void testUpdateUserWithNullName(){
        User user = createUser("Honza", "password", "user");
        userDao.addUser(user);
        user.setAccountName(null);
        userDao.updateUser(user);
    }
    
    @Test(expected=DataAccessException.class)
    public void testUpdateUserWithNullPass(){
        User user = createUser("Honza", "password", "user");
        userDao.addUser(user);
        user.setPassword(null);
        userDao.updateUser(user);   
    }
    
    @Test(expected=DataAccessException.class)
    public void testUpdateUserWithNullRole(){
        User user = createUser("Honza", "password", "user");
        userDao.addUser(user);
        user.setRole(null);
        userDao.updateUser(user);
    }
    
    //FIND BY ID Test
    
//    @Test
//    public void testFindUserById(){
//        User user = createUser("Honza", "password", "user");
//        userDao.addUser(user);
//        User userFromDb = em.find(User.class, user.getId());
//        assertDeepEquals(user,userFromDb);
//    }
    
    @Test(expected=DataAccessException.class)
    public void testFindUserByIdNull(){
        userDao.findUserById(null);
    }
    
    @Test
    public void testFindUserByName(){
        User user = createUser("Honza", "password", "user");
        userDao.addUser(user);
        User userFromDb = em.find(User.class, user.getAccountName());
        assertDeepEquals(user,userFromDb);
    }
    
    @Test(expected=DataAccessException.class)
    public void testFindUserByNameNull(){
        userDao.findUserByAccountName(null);
    }
    
    @Test(expected=DataAccessException.class)
    public void testDeleteUser(){
        User user1 = createUser("Honza", "password", "user");
        userDao.addUser(user1);
        User user2 = createUser("Tonik", "1234", "user");
        userDao.addUser(user2);

        user1 = userDao.findUserById(user1.getId());
        user2 = userDao.findUserById(user2.getId());
        assertNotNull(user1);
        assertNotNull(user2);

        userDao.deleteUser(user1);
        assertNotNull(userDao.findUserById(user2.getId()));
        
        userDao.findUserById(user1.getId());  
    }
    
    @Test(expected=DataAccessException.class)
    public void testDeleteUserNull(){
        userDao.deleteUser(null);  
    }
    
    @Test
    public void testGetAllUsers(){
        List<User> before = userDao.getAllUsers();
                
        User user1 = createUser("Honza", "password", "user");
        userDao.addUser(user1);
        User user2 = createUser("Tonik", "1234", "user");
        userDao.addUser(user2);
        
        List<User> after = userDao.getAllUsers();
        
        
        assertEquals(before.size()+2, after.size());
    }
        
    private void assertDeepEquals(User user1, User user2){
        assertEquals(user1.getId(), user2.getId());
        assertEquals(user1.getAccountName(), user2.getAccountName());
        assertEquals(user1.getPassword(), user2.getPassword());
        assertEquals(user1.getRole(), user2.getRole());
    }
    
    private void assertDeepEquals(List<User> user1,List<User> user2){
        for (int i = 0; i < user1.size(); i++){
            User fromFirst = user1.get(i);
            User fromSecond = user2.get(i);
            assertDeepEquals(fromFirst,fromSecond);
        }
    }
    
    private static User createUser(String name, String password, String role) {
        User user = new User();
        user.setAccountName(name);
        user.setPassword(password);
        user.setRole(role);
        return user;
    }
      
    private static Comparator<User> idComparator = new Comparator<User>() {
        @Override
        public int compare(User user1, User user2) {
            
            return user1.getId().compareTo(user2.getId());
        }
    };
}
