/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import cz.muni.fi.pa165.BaseTest;
import cz.muni.fi.pa165.legomanager.LegoDaoException;
import cz.muni.fi.pa165.legomanager.LegoSetDao;
import cz.muni.fi.pa165.legomanager.entity.LegoSet;
import cz.muni.fi.pa165.legomanager.services.LegoSetService;
import cz.muni.fi.pa165.legomanager.transferobjects.LegoSetTO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.dozer.DozerBeanMapper;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Petr Konecny
 * 
 */
public class LegoSetServiceImplTest extends BaseTest {
    
    @InjectMocks
    private LegoSetService setService;
    
    @Mock
    private LegoSetDao legoSetDao;
    
    @Autowired 
    DozerBeanMapper mapper;
    
    LegoSet set;
    LegoSetTO setTO;
    
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        set = map(getValidSet());
        setTO = getValidSet();
    }
          
    @Test
    public void testCreate(){
        setService.createLegoSet(setTO);
        verify(legoSetDao, times(1)).addLegoSet(set);
        verifyNoMoreInteractions(legoSetDao);     
    }
    
    @Test(expected=DataAccessException.class)
    public void testCreateWithException(){
        doThrow(LegoDaoException.class).when(legoSetDao).addLegoSet(set);
        setService.createLegoSet(setTO);
        doThrow(IllegalArgumentException.class).when(legoSetDao).addLegoSet(set);
        setService.createLegoSet(setTO);
    }
    
    @Test
    public void testUpdate(){
        setService.updateLegoSet(setTO);
        verify(legoSetDao, times(1)).updateLegoSet(set);
        verifyNoMoreInteractions(legoSetDao);     
    }
    
    @Test(expected=DataAccessException.class)
    public void testupdateWithException(){
        doThrow(LegoDaoException.class).when(legoSetDao).updateLegoSet(set);
        setService.updateLegoSet(setTO);
        doThrow(IllegalArgumentException.class).when(legoSetDao).updateLegoSet(set);
        setService.updateLegoSet(setTO);
    }
    
    @Test
    public void testRemove(){
        setService.removeLegoSet(setTO);
        verify(legoSetDao, times(1)).deleteLegoSet(set);
        verifyNoMoreInteractions(legoSetDao);     
    }
    
    @Test(expected=DataAccessException.class)
    public void testRemoveWithException(){
        doThrow(LegoDaoException.class).when(legoSetDao).deleteLegoSet(set);
        setService.removeLegoSet(setTO);
        doThrow(IllegalArgumentException.class).when(legoSetDao).deleteLegoSet(set);
        setService.removeLegoSet(setTO);
    }
    
    @Test
    public void testGet(){
        LegoSetTO expected = getValidSet();
        when(legoSetDao.findLegoSetById(-1L)).thenReturn(map(expected));
        verify(legoSetDao, times(1)).findLegoSetById(-1L);
        LegoSetTO actual = setService.getLegoSet(-1L);
        verifyNoMoreInteractions(legoSetDao);     
        assertDeepEquals(expected,actual);
    }
    
    @Test(expected=DataAccessException.class)
    public void getWithException(){
        doThrow(LegoDaoException.class).when(legoSetDao).findLegoSetById(-1L);
        setService.getLegoSet(-1L);
        doThrow(IllegalArgumentException.class).when(legoSetDao).findLegoSetById(-1L);
        setService.getLegoSet(-1L);
    }
       
    @Test
    public void testGetAll(){
        
        List expected  = new ArrayList<>();
        expected.add(setTO);
        expected.add(setTO);
        when(legoSetDao.getAllLegoSets()).thenReturn(map(expected));
        verify(legoSetDao, times(1)).getAllLegoSets();
        List actual = setService.getAllLegoSets();
        verifyNoMoreInteractions(legoSetDao);     
        assertDeepEquals(expected,actual);
    }
    
    @Test(expected=DataAccessException.class)
    public void getAllWithException(){
        doThrow(LegoDaoException.class).when(legoSetDao).getAllLegoSets();
        setService.getAllLegoSets();
        doThrow(IllegalArgumentException.class).when(legoSetDao).getAllLegoSets();
        setService.getAllLegoSets();
    }
    
           
    public static LegoSetTO getValidSet(){
        
        LegoSetTO set = new LegoSetTO();
        set.setId(-1L);
        set.setCategories(new HashSet());
        set.setLegoKits(new ArrayList());
        set.setName("Star Wars");
        set.setPrice(BigDecimal.ONE);
        return set;
    }
    
    public LegoSet map (LegoSetTO legoSet){
        return mapper.map(legoSet,LegoSet.class);
    }
    
    public List<LegoSet> map (List<LegoSetTO> legoSets){
        List list = new ArrayList<>();
        for(LegoSetTO set : legoSets){
            list.add(map(set));
        }
        return list;
    }
    
    private void assertDeepEquals(LegoSetTO set1, LegoSetTO set2){
     assertEquals(set1.getId(), set2.getId());
     assertEquals(set1.getPrice(), set2.getPrice());
     assertEquals(set1.getName(), set2.getName());
     assertEquals(set1.getCategories(), set2.getCategories());
     assertEquals(set1.getLegoKits(),set2.getLegoKits());
    }
       
    private void assertDeepEquals(List<LegoSetTO> set1,List<LegoSetTO> set2){
        for (int i = 0; i < set1.size(); i++){
            LegoSetTO fromFirst = set1.get(i);
            LegoSetTO fromSecond = set2.get(i);
            assertDeepEquals(fromFirst,fromSecond);
        }
    }
            
}
