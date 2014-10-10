/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165;

import cz.muni.fi.pa165.legomanager.entity.Category;
import cz.muni.fi.pa165.legomanager.LegoDaoException;
import cz.muni.fi.pa165.legomanager.LegoSetDao;
import cz.muni.fi.pa165.legomanager.entity.LegoKit;
import cz.muni.fi.pa165.legomanager.entity.LegoSet;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Petr Konecny
 */

public class LegoSetImplTest extends BaseTest {
  
   @Autowired
   LegoSetDao legoSetDao;
   @PersistenceContext
   EntityManager em;
    
   
       
    @Test
    public void testCreate(){
        
        LegoSet set = createLegoSet("Star Wars",new BigDecimal(10),new ArrayList<LegoKit>(),new HashSet<Category>());
        try{
            legoSetDao.addLegoSet(set);
        }catch(Exception ex){
         fail("Exception thrown"+ex.toString());
        }       
    }
       
    @Test(expected=IllegalArgumentException.class)
    public void testCreateSetWithNullArgument(){
        
         legoSetDao.addLegoSet(null);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testCreateSetWithNullPrice(){
                
         LegoSet set = createLegoSet("Star Wars",null,new ArrayList<LegoKit>(),new HashSet<Category>());
         legoSetDao.addLegoSet(set);
        
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testCreateSetWithNullName(){

        LegoSet set = createLegoSet(null,new BigDecimal(10),new ArrayList<LegoKit>(),new HashSet<Category>());
        legoSetDao.addLegoSet(set);
      
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testCreateSetWithNegativePrice(){ 
        
        LegoSet set = createLegoSet("Star Wars",new BigDecimal(-10),new ArrayList<LegoKit>(),new HashSet<Category>());
        legoSetDao.addLegoSet(set);
       
    }
    
   //@Test
    public void testUpdateSet(){
        LegoSet set = createLegoSet("Star Wars",new BigDecimal(10),new ArrayList<LegoKit>(),new HashSet<Category>());
        legoSetDao.addLegoSet(set);

    }
    
    @Test
    public void testFindSetById(){
        LegoSet set = createLegoSet("Castle",new BigDecimal(100.00),new ArrayList<LegoKit>(),new HashSet<Category>());
        legoSetDao.addLegoSet(set);
        LegoSet setFromDb = em.find(LegoSet.class, set.getId());
        System.out.println(set.getId());
        assertDeepEquals(set,setFromDb);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testFindSetByIdNull(){
        LegoSet setFromDb = legoSetDao.findLegoSetById(null);
    }
    
    @Test(expected=LegoDaoException.class)
    public void testDeleteSet(){
        LegoSet set1 = createLegoSet("Star Wars",new BigDecimal(10),new ArrayList<LegoKit>(),new HashSet<Category>());
        LegoSet set2 = createLegoSet("Cast;e",new BigDecimal(100),new ArrayList<LegoKit>(),new HashSet<Category>());
        legoSetDao.addLegoSet(set1);
        legoSetDao.addLegoSet(set2);

        set1 = legoSetDao.findLegoSetById(set1.getId());
        set2 = legoSetDao.findLegoSetById(set2.getId());
        assertNotNull(set1);
        assertNotNull(set2);

        legoSetDao.deleteLegoSet(set1);
        assertNotNull(legoSetDao.findLegoSetById(set2.getId()));
        
        legoSetDao.findLegoSetById(set1.getId());  
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testDeleteSetNull(){
        legoSetDao.deleteLegoSet(null);  
    }
    
    @Test(expected=LegoDaoException.class)
    public void testDeleteNonExistingSet(){
        LegoSet set1 = createLegoSet("Star Wars",new BigDecimal(10),new ArrayList<LegoKit>(),new HashSet<Category>());
        legoSetDao.deleteLegoSet(set1);
        
    }
    
    @Test
    public void testGetAllKits(){
        assertTrue(legoSetDao.getAllLegoSets().isEmpty());
        LegoSet set1 = createLegoSet("Castle",new BigDecimal(100),new ArrayList<LegoKit>(),new HashSet<Category>());
        LegoSet set2 = createLegoSet("Star Wars",new BigDecimal(10),new ArrayList<LegoKit>(),new HashSet<Category>());
        legoSetDao.addLegoSet(set1);
        legoSetDao.addLegoSet(set2);
        
        List<LegoSet> expected = Arrays.asList(set1,set2);
        List<LegoSet> actual = legoSetDao.getAllLegoSets();
        
        Collections.sort(actual,idComparator);
        Collections.sort(expected,idComparator);
        
        assertEquals(expected, actual);
        assertDeepEquals(expected, actual);     
    }
    
    @Test
    public void testGetCategoriesFromSet(){
        
        Category category = new Category();
        category.setName("For kids");
        Set categories = new HashSet<Category>();
        categories.add(category);
        LegoSet set1 = createLegoSet("Castle",new BigDecimal(100),new ArrayList<LegoKit>(),categories);
        em.persist(set1);
        LegoSet setFromDb = em.find(LegoSet.class, set1.getId());
        assertTrue(!setFromDb.getCategories().isEmpty());
    }
    
     @Test
    public void testGetKitsFromSet(){
        
        LegoKit kit = new LegoKit();
        kit.setName("Star Wars");
        List kits = new ArrayList<LegoKit>();
        kits.add(kit);
        LegoSet set1 = createLegoSet("Castle",new BigDecimal(100),kits,new HashSet());
        em.persist(set1);
        LegoSet setFromDb = em.find(LegoSet.class, set1.getId());
        assertTrue(!setFromDb.getLegoKits().isEmpty());
    }
    
        
    private void assertDeepEquals(LegoSet set1, LegoSet set2){
        assertEquals(set1.getId(), set2.getId());
        assertEquals(set1.getPrice(), set2.getPrice());
        assertEquals(set1.getName(), set2.getName());
        assertEquals(set1.getCategories(), set2.getCategories());
        assertEquals(set1.getLegoKits(),set2.getLegoKits());
    }
    
    private void assertDeepEquals(List<LegoSet> set1,List<LegoSet> set2){
        for (int i = 0; i < set1.size(); i++){
            LegoSet fromFirst = set1.get(i);
            LegoSet fromSecond = set2.get(i);
            assertDeepEquals(fromFirst,fromSecond);
        }
    }
    
    private static LegoSet createLegoSet(String name, BigDecimal price, List<LegoKit> list, Set<Category> categories) {
        LegoSet set = new LegoSet();
        set.setPrice(price);
        set.setName(name);
        set.setLegoKits(list);
        set.setCategories(categories);
        return set;
    }
      
    private static Comparator<LegoSet> idComparator = new Comparator<LegoSet>() {
        @Override
        public int compare(LegoSet set1, LegoSet set2) {
            
            return set1.getId().compareTo(set2.getId());
        }
    };
}
