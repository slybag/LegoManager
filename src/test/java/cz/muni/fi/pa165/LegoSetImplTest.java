/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165;

import cz.muni.fi.pa165.legomanager.entity.Category;
import cz.muni.fi.pa165.legomanager.LegoDaoException;
import cz.muni.fi.pa165.legomanager.entity.LegoKit;
import cz.muni.fi.pa165.legomanager.entity.LegoSet;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import org.junit.After;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Petr
 */
 
public class LegoSetImplTest  {
  
 /*   
    EntityManager em;
    @PersistenceUnit
    EntityManagerFactory emf;
    LegoSetDao legoSetDao;
    
    @Before
    public void init(){
        em = emf.createEntityManager();
    }
    
    @Before
    public void initTest(){
        em = emf.createEntityManager();
    }
    
    @After
    public void close() {
        em.clear();
        em.close();
    }
    
    @Test
    public void testCreate(){
        
        LegoSet set = createLegoSet("Star Wars",new BigDecimal(10),null,null);
        try{
            legoSetDao.createLegoSet(set);
        }catch(Exception ex){
         fail("Exception thrown"+ex.getMessage());
        }       
    }
    
    @Test
    public void testCreateSetWithNullArgument(){
        
         try{
            legoSetDao.createLegoSet(null);
            fail("No exception thrown");
        } catch(IllegalArgumentException ex) {
        } catch (Exception ex) {
            fail("IllegalArgumentException expected, thrown:" + ex.getMessage());
        }
        
    }
    
    @Test
    public void testCreateSetWithNullPrice(){
                
         LegoSet set = createLegoSet("Star Wars",null,null,null);
         try{
             legoSetDao.createLegoSet(set);
         }catch(IllegalArgumentException ex) {
         }catch(Exception ex){
            fail("IllegalArgumentException expected, thrown:" + ex.getMessage());
         }
    }
    
    @Test
    public void testCreateSetWithNullName(){

        LegoSet set = createLegoSet(null,new BigDecimal(10),null,null);
        try{
            legoSetDao.createLegoSet(set);
        }catch(IllegalArgumentException ex) {
        }catch(Exception ex){
           fail("IllegalArgumentException expected, thrown:" + ex.getMessage());
       }
    }
    
    @Test
    public void testCreateSetWithNegativePrice(){ 
        
        LegoSet set = createLegoSet("Star Wars",new BigDecimal(-10),null,null);
         try{
             legoSetDao.createLegoSet(set);
         }catch(IllegalArgumentException ex) {
         }catch(Exception ex){
            fail("IllegalArgumentException expected, thrown:" + ex.getMessage());
         } 
    }
    
    @Test
    public void testUpdateSet(){
        LegoSet set = createLegoSet("Star Wars",new BigDecimal(10),null,null);
        legoSetDao.createSet(set);

    }
    
    @Test
    public void testFindSetById(){
        LegoSet set = createLegoSet("Castle",new BigDecimal(100),null,null);
        em.persist(set);
        LegoSet setFromDb = em.find(LegoSet.class, set.getId());
        assertDeepEquals(set,setFromDb);
    }
    
    @Test
    public void testFindSetByIdNull(){
        try{
        LegoSet setFromDb = em.find(LegoSet.class, null);
        }catch(IllegalArgumentException ex){
        }catch(Exception ex){
        fail("IllegalArgumentException expected, thrown:" + ex.getMessage());
        }
    }
    
    @Test
    public void testDeleteSet(){
        LegoSet set1 = createLegoSet("Star Wars",new BigDecimal(10),null,null);
        LegoSet set2 = createLegoSet("Cast;e",new BigDecimal(100),null,null);
        legoSetDao.createLegoSet(set1);
        legoSetDao.createLegoSet(set2);

        assertNotNull(legoSetDao.getLegoSetById(set1.getId()));
        assertNotNull(legoSetDao.getLegoSetById(set2.getId()));

        legoSetDao.removeLegoSet(set1);
        assertNotNull(legoSetDao.getLegoSet(set2.getId()));
        
        try {
            LegoSet removedSet = legoSetDao.getSteward(set1.getId());
        } catch (LegoDaoException ex) {
            return;
        }
        fail();
       
    }
    
    @Test
    public void testDeleteSetNull(){
        try{
        legoSetDao.removeLegoSet(null);
        }catch(IllegalArgumentException ex){
        }catch(Exception ex){
        fail("IllegalArgumentException expected, thrown:" + ex.getMessage());
        }
    }
    
    @Test
    public void testDeleteNonExistingSet(){
        try{
        LegoSet set1 = createLegoSet("Star Wars",new BigDecimal(10),null,null);
        legoSetDao.removeLegoSet(set1);
        }catch(LegoDaoException ex){
        }catch(Exception ex){
        fail("LegoDaoException expected, thrown:" + ex.getMessage());
        }
    }
    
    @Test
    public void testGetAllKits(){
        assertTrue(legoSetDao.getAllKits().isEmpty());
        LegoSet set1 = createLegoSet("Castle",new BigDecimal(100),null,null);
        LegoSet set2 = createLegoSet("Star Wars",new BigDecimal(10),null,null);
        legoSetDao.createLegoSet(set1);
        legoSetDao.createLegoSet(set2);
        
        List<LegoSet> expected = Arrays.asList(set1,set2);
        List<LegoKit> actual = legoSetDao.getAllSets();
        
        Collections.sort(actual,idComparator);
        Collections.sort(expected,idComparator);
        
        assertEquals(expected, actual);
        assertDeepEquals(expected, actual);     
    }
        
    private void assertDeepEquals(LegoSet set1, LegoSet set2){
        assertEquals(set1.getId(), set2.getId());
        assertEquals(set1.getPrice(), set2.getPrice());
        assertEquals(set1.getName(), set2.getName());
        assertEquals(set1.getCategory(), set2.getCategory());
        assertEquals(set1.getLegoKits(),set2.getLegoKits());
    }
    
    private void assertDeepEquals(List<LegoSet> set1,List<LegoSet> set2){
        for (int i = 0; i < set1.size(); i++){
            LegoSet fromFirst = set1.get(i);
            LegoSet fromSecond = set2.get(i);
            assertDeepEquals(fromFirst,fromSecond);
        }
    }
    
    private static LegoSet createLegoSet(String name, BigDecimal price, List<legoKit> list, Category category) {
        LegoSet set = new LegoSet();
        set.setName(name);
        set.setPrice(price);
        set.setLegoKits(list);
        set.setCategory(category);
        return set;
    }
    
    /**
     * Comparator by id.
     */
    /**
    private static Comparator<LegoSet> idComparator = new Comparator<LegoSet>() {
        @Override
        public int compare(LegoSet set1, LegoSet set2) {
            return set1.getId().compareTo(set2.getId());
        }
    };
    */
}
