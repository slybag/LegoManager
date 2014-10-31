/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165;

import cz.muni.fi.pa165.legomanager.LegoKitDao;
import cz.muni.fi.pa165.legomanager.LegoSetDao;
import cz.muni.fi.pa165.legomanager.entity.LegoKit;
import cz.muni.fi.pa165.legomanager.entity.LegoSet;
import cz.muni.fi.pa165.legomanager.services.LegoSetService;
import cz.muni.fi.pa165.legomanager.services.impl.LegoKitServiceImpl;
import cz.muni.fi.pa165.legomanager.transferobjects.LegoKitTO;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;

/**
 *
 * @author Petr
 * 
 */
public class LegoSetServiceImplTest extends BaseTest {
    
    @InjectMocks
    @Autowired
    private LegoSetService setService;
    
    @Mock
    private LegoSetDao legoSetDao;
    
    @Autowired 
    DozerBeanMapper mapper;
    
    @Before
    public void setUpMock(){
        MockitoAnnotations.initMocks(this);
    }
          
    @Test
    public void testCreate(){
        LegoSetTO expected = getValidSet();
        setService.createLegoSet(expected);
        verify(legoSetDao).addLegoSet(map(expected));       
    }
    
    @Test(expected=DataAccessException.class)
    public void testCreateWithException(){
        doThrow(Exception.class).when(legoSetDao).addLegoSet(null);
        setService.createLegoSet(null);
    }
    
    @Test
    public void testUpdate(){
        LegoSetTO expected = getValidSet();
        setService.updateLegoSet(expected);
        verify(legoSetDao).updateLegoSet(map(expected));
    }
    
    @Test
    public void testRemove(){
        LegoSetTO set = getValidSet();
        setService.removeLegoSet(set);
        verify(legoSetDao).deleteLegoSet(map(set));
    }
    
    @Test
    public void testGet(){
        LegoSetTO expected = getValidSet();
        when(legoSetDao.findLegoSetById(-1L)).thenReturn(map(expected));
        LegoSetTO actual = setService.getLegoSet(-1L);
        assertDeepEquals(expected,actual);
    }
       
    public void testGetAll(){
        
        List expected = new ArrayList();
        expected.add(getValidSet());
        expected.add(getValidSet());
        when(legoSetDao.getAllLegoSets()).thenReturn(map(expected));

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
            
}
