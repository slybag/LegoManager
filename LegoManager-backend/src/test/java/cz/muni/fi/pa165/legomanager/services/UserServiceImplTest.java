/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.legomanager.services;

import cz.muni.fi.pa165.legomanager.dao.UserDao;
import cz.muni.fi.pa165.legomanager.entity.User;
import cz.muni.fi.pa165.legomanager.transferobjects.UserTO;
import java.util.ArrayList;
import java.util.List;
import org.dozer.DozerBeanMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author xrais
 */
public class UserServiceImplTest extends BaseServiceTest {
    
    @Autowired
    @InjectMocks
    private UserService userService;
    
    @Mock
    private UserDao userDao;
    
    @Autowired
    private DozerBeanMapper mapper;
    
    private User user;
    
    private UserTO userTO;
    
    @Before
    public void setUp(){
        user = map(getValidUser());
        userTO = getValidUser();
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testCreate(){
        userService.createUser(userTO);
        verify(userDao, times(1)).addUser(user);
        verifyNoMoreInteractions(userDao);     
    }        
    
    @Test(expected=IllegalArgumentException.class)
    public void updateWithNull(){
        doThrow(Exception.class).when(userDao).updateUser(null);
        userService.updateUser(null);
    }
    
    @Test
    public void testRemove(){
        userService.deleteUser(userTO);
        verify(userDao, times(1)).deleteUser(user);
        verifyNoMoreInteractions(userDao);     
    }
       
    @Test(expected=IllegalArgumentException.class)
    public void removeWithNull(){
        doThrow(Exception.class).when(userDao).deleteUser(null);
        userService.deleteUser(null);
    }
    
    @Test
    public void testGet(){
        UserTO expected = getValidUser();
        when(userDao.findUserByAccountName("name")).thenReturn(map(expected));
        UserTO actual = userService.getUser("name");
        verify(userDao, times(1)).findUserByAccountName("name");
        verifyNoMoreInteractions(userDao);     
        assertDeepEquals(expected,actual);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void getWithNull(){
        doThrow(Exception.class).when(userDao).findUserById(null);
        userService.getUser(null);
    }

    @Test
    public void testGetAll(){
        
        List expected  = new ArrayList<>();
        expected.add(userTO);
        expected.add(userTO);
        when(userDao.getAllUsers()).thenReturn(map(expected));
        List actual = userService.getAllUsers();
        verify(userDao, times(1)).getAllUsers();
        verifyNoMoreInteractions(userDao);     
        assertDeepEquals(expected,actual);
    }
        
    private UserTO getValidUser() {
        
        UserTO user1TO = new UserTO();
        user1TO.setAccountName("name");
        user1TO.setPassword("pass");
        user1TO.setRole("ROLE_USER");
        
        return user1TO;
    }

    private User map(UserTO validUser) {
        return mapper.map(validUser,User.class);
    }
    
    public List<User> map (List<UserTO> users){
        List list = new ArrayList<>();
        for(UserTO user : users){
            list.add(map(user));
        }
        return list;
    }
    
    private void assertDeepEquals(UserTO user1, UserTO user2){
        assertEquals(user1.getAccountName(), user2.getAccountName());
        assertEquals(user1.getPassword(), user2.getPassword());
        assertEquals(user1.getRole(), user2.getRole());
    }
    
    private void assertDeepEquals(List<UserTO> user1,List<UserTO> user2){
        for (int i = 0; i < user1.size(); i++){
            UserTO fromFirst = user1.get(i);
            UserTO fromSecond = user2.get(i);
            assertDeepEquals(fromFirst,fromSecond);
        }
    }
}
