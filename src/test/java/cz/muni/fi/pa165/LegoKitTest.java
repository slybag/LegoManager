/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165;

import cz.muni.fi.pa165.legomanager.LegoKitDao;
import cz.muni.fi.pa165.legomanager.LegoKitDaoImpl;
import cz.muni.fi.pa165.legomanager.entity.Category;
import cz.muni.fi.pa165.legomanager.entity.LegoKit;
import cz.muni.fi.pa165.legomanager.entity.LegoPiece;
import cz.muni.fi.pa165.legomanager.entity.LegoSet;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;

/**
 *
 * @author Tomas
 */
@ContextConfiguration(classes=LegoKit.class)
@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD)
public class LegoKitTest {
    
    @PersistenceUnit
    EntityManagerFactory emf;
    LegoKitDao legoKitDao;
    
    @Before
    public void setup(){
        EntityManager em;
        em = emf.createEntityManager();
        legoKitDao = new LegoKitDaoImpl();
    }
    
    @Test
    public void testCreate(){
        EntityManager em = emf.createEntityManager();
        LegoKit kit = createLegoKit("Death Star", new BigDecimal(42), 12, null, null, null);
        try{
            legoKitDao.addLegoKit(kit);
        }catch(Exception ex){
         //fail("Exception thrown"+ex.getMessage());
        }       
    }
    
    @Test
    public void testDelete(){
        
        LegoKit kit = createLegoKit("Death Star", new BigDecimal(42), 12, null, null, null);
        try{
            legoKitDao.addLegoKit(kit);
        }catch(Exception ex){
         //fail("Exception thrown"+ex.getMessage());
        }       
    }
    
    private LegoKit createLegoKit(String name, BigDecimal price, int ageRestriction, Set<Category> categories, List<LegoPiece> legoPieces, List<LegoSet> legoSets){
        LegoKit kit = new LegoKit();
        kit.setName(name);
        kit.setPrice(price);
        kit.setAgeRestriction(ageRestriction);
        kit.setCategories(categories);
        kit.setLegoPieces(legoPieces);
        kit.setLegoSets(legoSets);
        return kit;
    }
}
