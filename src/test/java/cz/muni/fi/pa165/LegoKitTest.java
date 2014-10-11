/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165;

import cz.muni.fi.pa165.legomanager.LegoDaoException;
import cz.muni.fi.pa165.legomanager.entity.Category;
import cz.muni.fi.pa165.legomanager.LegoKitDao;
import cz.muni.fi.pa165.legomanager.entity.LegoKit;
import cz.muni.fi.pa165.legomanager.entity.LegoPiece;
import cz.muni.fi.pa165.legomanager.entity.LegoSet;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Tomas
 */
public class LegoKitTest extends BaseTest{
    
    @Autowired
    LegoKitDao legoKitDao;

    @PersistenceUnit
    EntityManagerFactory emf;
    
    @PersistenceContext
    EntityManager em;
    
    @Test
    public void addLegoKitTest() {
        List<LegoKit> legoKitsBefore = em.createQuery("SELECT l FROM LegoKit l", LegoKit.class).getResultList();
        int sizeBefore = legoKitsBefore.size();
        LegoKit legoKit1 = createLegoKit("Star Wars Death Star", BigDecimal.valueOf(42), 12, new HashSet<Category>(), new ArrayList<LegoPiece>(), new ArrayList<LegoSet>());
        try {
            legoKitDao.addLegoKit(legoKit1);
        } catch (Exception e) {
            fail("Exception thrown adding lego kit: " + e.getMessage());
        }
        List<LegoKit> legoKitsAfter = em.createQuery("SELECT l FROM LegoKit l", LegoKit.class).getResultList();
        int sizeAfter = legoKitsAfter.size();
        assertEquals(sizeBefore + 1, sizeAfter);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void addLegoNullTest(){
        legoKitDao.addLegoKit(null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void addLegoNameNullTest(){
        LegoKit legoKit = createLegoKit(null, BigDecimal.valueOf(42), 12, new HashSet<Category>(), new ArrayList<LegoPiece>(), new ArrayList<LegoSet>());
        legoKitDao.addLegoKit(legoKit);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void addLegoPriceNullTest(){
        LegoKit legoKit = createLegoKit("StarWars Death Star", null, 12, new HashSet<Category>(), new ArrayList<LegoPiece>(), new ArrayList<LegoSet>());
        legoKitDao.addLegoKit(legoKit);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void addLegoAgeRestrictionNullTest(){
        LegoKit legoKit = createLegoKit("StarWars Death Star", BigDecimal.TEN, null, new HashSet<Category>(), new ArrayList<LegoPiece>(), new ArrayList<LegoSet>());
        legoKitDao.addLegoKit(legoKit);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void addLegoCategoriesNullTest(){
        LegoKit legoKit = createLegoKit("StarWars Death Star", BigDecimal.TEN, 12, null, new ArrayList<LegoPiece>(), new ArrayList<LegoSet>());
        legoKitDao.addLegoKit(legoKit);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void addLegoPiecesNullTest(){
        LegoKit legoKit = createLegoKit("StarWars Death Star", BigDecimal.TEN, 12, new HashSet<Category>(), null, new ArrayList<LegoSet>());
        legoKitDao.addLegoKit(legoKit);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void addLegoSetsNullTest(){
        LegoKit legoKit = createLegoKit("StarWars Death Star", BigDecimal.TEN, null, new HashSet<Category>(), new ArrayList<LegoPiece>(), null);
        legoKitDao.addLegoKit(legoKit);
    }

    @Test
    public void getAllLegoKitsTest() {
        List<LegoKit> legoKitsBefore = legoKitDao.getAllLegoKits();
        legoKitDao.addLegoKit(createLegoKit("StarWars Death Star", new BigDecimal(42), 12, new HashSet<Category>(), new ArrayList<LegoPiece>(), new ArrayList<LegoSet>()));
        legoKitDao.addLegoKit(createLegoKit("Pokemon Charizard", new BigDecimal(41), 13, new HashSet<Category>(), new ArrayList<LegoPiece>(), new ArrayList<LegoSet>()));
        legoKitDao.addLegoKit(createLegoKit("Model Truck", new BigDecimal(43), 15, new HashSet<Category>(), new ArrayList<LegoPiece>(), new ArrayList<LegoSet>()));
        
        List<LegoKit> legoKitsAfter = legoKitDao.getAllLegoKits();
        
        Assert.assertEquals(legoKitsBefore.size() + 3, legoKitsAfter.size());
    }

    @Test
    public void findLegoKitByIdTest() {
        LegoKit legoKitAdded = createLegoKit("Pokemon Charizard", new BigDecimal(42), 12, new HashSet<Category>(), new ArrayList<LegoPiece>(), new ArrayList<LegoSet>());
        legoKitDao.addLegoKit(legoKitAdded);
        Long id = legoKitAdded.getId();
        
        LegoKit legoKitFound = legoKitDao.findLegoKitById(id);

        Assert.assertEquals(legoKitAdded.getName(), legoKitFound.getName());
    }

    @Test
    public void updateLegoKitTest() {
        LegoKit legoKit1 = createLegoKit("Pokemon Charizard", new BigDecimal(42), 12, new HashSet<Category>(), new ArrayList<LegoPiece>(), new ArrayList<LegoSet>());
        legoKitDao.addLegoKit(legoKit1);
        
        try {
            legoKitDao.updateLegoKit(legoKit1);
        } catch (Exception ex) {
            fail("Update throws exception " + ex);
        }
    }
    
    @Test
    public void updateLegoKitNameTest(){
        LegoKit legoKit1 = createLegoKit("Pokemon Charizard", new BigDecimal(42), 12, new HashSet<Category>(), new ArrayList<LegoPiece>(), new ArrayList<LegoSet>());
        legoKitDao.addLegoKit(legoKit1);
        legoKit1.setName("Model Truck");
        try{
            legoKitDao.updateLegoKit(legoKit1);
            LegoKit updated = legoKitDao.findLegoKitById(legoKit1.getId());
            assertEquals(updated.getName(), "Model Truck");
        } catch (Exception ex){
            fail("Updating name throws exception " + ex);
        }
    }
    
    @Test
    public void updateLegoKitPriceTest(){
        LegoKit legoKit1 = createLegoKit("Pokemon Charizard", new BigDecimal(42), 12, new HashSet<Category>(), new ArrayList<LegoPiece>(), new ArrayList<LegoSet>());
        legoKitDao.addLegoKit(legoKit1);
        legoKit1.setPrice(BigDecimal.TEN);
        try{
            legoKitDao.updateLegoKit(legoKit1);
            LegoKit updated = legoKitDao.findLegoKitById(legoKit1.getId());
            assertEquals(updated.getPrice(), BigDecimal.TEN);
        } catch (Exception ex){
            fail("Updating price throws exception " + ex);
        }
    }
    
    @Test
    public void updateLegoKitAgeRestrictionTest(){
        LegoKit legoKit1 = createLegoKit("Pokemon Charizard", new BigDecimal(42), 12, new HashSet<Category>(), new ArrayList<LegoPiece>(), new ArrayList<LegoSet>());
        legoKitDao.addLegoKit(legoKit1);
        legoKit1.setAgeRestriction(42);
        try{
            legoKitDao.updateLegoKit(legoKit1);
            LegoKit updated = legoKitDao.findLegoKitById(legoKit1.getId());
            assertEquals(updated.getAgeRestriction(), 42);
        } catch (Exception ex){
            fail("Updating age restriction throws exception " + ex);
        }
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void updateLegoKitNullTest(){
        LegoKit legoKit1 = createLegoKit("Pokemon Charizard", new BigDecimal(42), 12, new HashSet<Category>(), new ArrayList<LegoPiece>(), new ArrayList<LegoSet>());
        legoKitDao.addLegoKit(legoKit1);
        legoKit1.setAgeRestriction(42);
        legoKitDao.updateLegoKit(null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void updateLegoKitNameNullTest(){
        LegoKit legoKit1 = createLegoKit("Pokemon Charizard", new BigDecimal(42), 12, new HashSet<Category>(), new ArrayList<LegoPiece>(), new ArrayList<LegoSet>());
        legoKitDao.addLegoKit(legoKit1);
        legoKit1.setName(null);
        legoKitDao.updateLegoKit(legoKit1);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void updateLegoKitPriceNullTest(){
        LegoKit legoKit1 = createLegoKit("Pokemon Charizard", new BigDecimal(42), 12, new HashSet<Category>(), new ArrayList<LegoPiece>(), new ArrayList<LegoSet>());
        legoKitDao.addLegoKit(legoKit1);
        legoKit1.setPrice(null);
        legoKitDao.updateLegoKit(legoKit1);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void updateLegoKitCategoriesNullTest(){
        LegoKit legoKit1 = createLegoKit("Pokemon Charizard", new BigDecimal(42), 12, new HashSet<Category>(), new ArrayList<LegoPiece>(), new ArrayList<LegoSet>());
        legoKitDao.addLegoKit(legoKit1);
        legoKit1.setCategories(null);
        legoKitDao.updateLegoKit(legoKit1);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void updateLegoKitPiecesNullTest(){
        LegoKit legoKit1 = createLegoKit("Pokemon Charizard", new BigDecimal(42), 12, new HashSet<Category>(), new ArrayList<LegoPiece>(), new ArrayList<LegoSet>());
        legoKitDao.addLegoKit(legoKit1);
        legoKit1.setLegoPieces(null);
        legoKitDao.updateLegoKit(legoKit1);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void updateLegoKitSetsNullTest(){
        LegoKit legoKit1 = createLegoKit("Pokemon Charizard", new BigDecimal(42), 12, new HashSet<Category>(), new ArrayList<LegoPiece>(), new ArrayList<LegoSet>());
        legoKitDao.addLegoKit(legoKit1);
        legoKit1.setLegoSets(null);
        legoKitDao.updateLegoKit(legoKit1);
    }

    @Test
    public void deleteLegoKitTest() {
        LegoKit legoKit1 = createLegoKit("Pokemon Charizard", new BigDecimal(42), Integer.MAX_VALUE, new HashSet<Category>(), new ArrayList<LegoPiece>(), new ArrayList<LegoSet>());
        legoKitDao.addLegoKit(legoKit1);
        LegoKit stored = legoKitDao.findLegoKitById(legoKit1.getId());
        
        
        List<LegoKit> legoKits = em.createQuery("SELECT l FROM LegoKit l", LegoKit.class).getResultList();
        int sizeBefore = legoKits.size();
        //OK
        try {
            legoKitDao.deleteLegoKit(stored);
        } catch (Exception ex) {
            fail ("Exception thrown: " + ex.getMessage());
        }
        legoKits = em.createQuery("SELECT l FROM LegoKit l", LegoKit.class).getResultList();
        int sizeAfter = legoKits.size();
        
        assertEquals(sizeBefore - 1, sizeAfter);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void deleteLegoKitNullTest(){
        legoKitDao.deleteLegoKit(null);
    }
    
    @Test(expected = LegoDaoException.class)
    public void deleteLegoKitNotExistTest(){
        LegoKit legoKit2 = createLegoKit("StarWars Death Star", new BigDecimal(42), 12, new HashSet<Category>(), new ArrayList<LegoPiece>(), new ArrayList<LegoSet>());
        legoKitDao.addLegoKit(legoKit2);
        LegoKit stored2 = legoKitDao.findLegoKitById(legoKit2.getId());      
        
        legoKitDao.deleteLegoKit(stored2);
        legoKitDao.deleteLegoKit(stored2);
    }
    
    private LegoKit createLegoKit(String name, BigDecimal price, Integer ageRestriction, Set<Category> categories, List<LegoPiece> legoPieces, List<LegoSet> legoSets){
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
