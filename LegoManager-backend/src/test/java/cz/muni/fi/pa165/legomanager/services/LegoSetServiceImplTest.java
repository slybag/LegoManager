/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.legomanager.services;

import cz.muni.fi.pa165.legomanager.dao.LegoSetDao;
import cz.muni.fi.pa165.legomanager.entity.LegoSet;
import cz.muni.fi.pa165.legomanager.services.impl.LegoSetServiceImpl;
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

/**
 *
 * @author Petr Konecny
 * 
 */

public class LegoSetServiceImplTest extends BaseServiceTest{
    
    @Autowired
    @InjectMocks
    private LegoSetService setService;
    
    @Mock
    private LegoSetDao legoSetDao;
    
    @Autowired
    private DozerBeanMapper mapper;
    
    private LegoSet set;
    
    private LegoSetTO setTO;
    
    @Before
    public void setUp(){
        set = map(getValidSet());
        setTO = getValidSet();
        MockitoAnnotations.initMocks(this);
    }
          
    @Test
    public void testCreate(){
        setService.createLegoSet(setTO);
        verify(legoSetDao, times(1)).addLegoSet(set);
        verifyNoMoreInteractions(legoSetDao);     
    }
    
    @Test
    public void testUpdate(){
        setService.updateLegoSet(setTO);
        verify(legoSetDao, times(1)).updateLegoSet(set);
        verifyNoMoreInteractions(legoSetDao);     
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void updateWithNull(){
        doThrow(Exception.class).when(legoSetDao).updateLegoSet(null);
        setService.updateLegoSet(null);
    }
    
    @Test
    public void testRemove(){
        setService.removeLegoSet(setTO);
        verify(legoSetDao, times(1)).deleteLegoSet(set);
        verifyNoMoreInteractions(legoSetDao);     
    }
       
    @Test(expected=IllegalArgumentException.class)
    public void removeWithNull(){
        doThrow(Exception.class).when(legoSetDao).deleteLegoSet(null);
        setService.removeLegoSet(null);
    }
    
    @Test
    public void testGet(){
        LegoSetTO expected = getValidSet();
        when(legoSetDao.findLegoSetById(-1L)).thenReturn(map(expected));
        LegoSetTO actual = setService.getLegoSet(-1L);
        verify(legoSetDao, times(1)).findLegoSetById(-1L);
        verifyNoMoreInteractions(legoSetDao);     
        assertDeepEquals(expected,actual);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void getWithNull(){
        doThrow(Exception.class).when(legoSetDao).findLegoSetById(null);
        setService.getLegoSet(null);
    }

    @Test
    public void testGetAll(){
        
        List expected  = new ArrayList<>();
        expected.add(setTO);
        expected.add(setTO);
        when(legoSetDao.getAllLegoSets()).thenReturn(map(expected));
        List actual = setService.getAllLegoSets();
        verify(legoSetDao, times(1)).getAllLegoSets();
        verifyNoMoreInteractions(legoSetDao);     
        assertDeepEquals(expected,actual);
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
