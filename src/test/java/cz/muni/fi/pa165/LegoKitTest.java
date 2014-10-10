/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165;

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
import javax.persistence.PersistenceUnit;
import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Tomas
 */
public class LegoKitTest {
    
    @Autowired
    LegoKitDao legoKitDao;

    @PersistenceUnit
    public EntityManagerFactory emf;
    EntityManager em;

    @Before
    public void setup() {
        em = emf.createEntityManager();
    }

    @After
    public void close() {
        em.close();
    }

    @Test
    public void addLegoKitTest() {
        LegoKit legoKit1 = createLegoKit(null, BigDecimal.ZERO, 42, null, null, null);
        try {
            legoKitDao.addLegoKit(legoKit1);
        } catch (Exception e) {
            fail("Exception thrown" + e.getMessage());
        }

        LegoKit piece = legoKitDao.findLegoKitById(legoKit1.getId());
        assertDeepEquals(piece, legoKit1);

        //with null
        try {
            legoKitDao.addLegoKit(null);
            fail("No exception thrown");
        } catch (Exception ex) {
        }
        
        LegoKit legoKitNull;
        //name is null
        legoKitNull = createLegoKit(null, BigDecimal.valueOf(42), 12, new HashSet<Category>(), new ArrayList<LegoPiece>(), new ArrayList<LegoSet>());
        try {
            legoKitDao.addLegoKit(legoKitNull);
            fail("LegoKit name can be null");
        } catch (Exception ex) {
        }
        //price is null
        legoKitNull = createLegoKit("StarWars Death Star", null, 12, new HashSet<Category>(), new ArrayList<LegoPiece>(), new ArrayList<LegoSet>());
        try {
            legoKitDao.addLegoKit(legoKitNull);
            fail("LegoKit price can be null");
        } catch (Exception ex) {
        }
        //ageRestriction is null
        legoKitNull = createLegoKit("StarWars Death Star", BigDecimal.valueOf(42), null, new HashSet<Category>(), new ArrayList<LegoPiece>(), new ArrayList<LegoSet>());
        try {
            legoKitDao.addLegoKit(legoKitNull);
            fail("LegoKit age restriction value can be null");
        } catch (Exception ex) {
        }
        //categories is null
        legoKitNull = createLegoKit("StarWars Death Star", BigDecimal.valueOf(42), 12, null, new ArrayList<LegoPiece>(), new ArrayList<LegoSet>());
        try {
            legoKitDao.addLegoKit(legoKitNull);
            fail("LegoKit categories set value can be null");
        } catch (Exception ex) {
        }
        //pieces is null
        legoKitNull = createLegoKit("StarWars Death Star", BigDecimal.valueOf(42), 12, new HashSet<Category>(), null, new ArrayList<LegoSet>());
        try {
            legoKitDao.addLegoKit(legoKitNull);
            fail("LegoKit piececs list value can be null");
        } catch (Exception ex) {
        }
        //sets is null
        legoKitNull = createLegoKit("StarWars Death Star", BigDecimal.valueOf(42), 12, new HashSet<Category>(), new ArrayList<LegoPiece>(), null);
        try {
            legoKitDao.addLegoKit(legoKitNull);
            fail("LegoKit lego sets list value can be null");
        } catch (Exception ex) {
        }
    }

    @Test
    public void getAllLegoKitsTest() {
        List<LegoKit> legoKits = em.createQuery("SELECT * FROM LegoKit l", LegoKit.class).getResultList();
        int sizeBefore = legoKits.size();
        legoKitDao.addLegoKit(createLegoKit("StarWars Death Star", new BigDecimal(42), 12, new HashSet<Category>(), new ArrayList<LegoPiece>(), new ArrayList<LegoSet>()));
        legoKitDao.addLegoKit(createLegoKit("Pokemon Charizard", new BigDecimal(41), 13, new HashSet<Category>(), new ArrayList<LegoPiece>(), new ArrayList<LegoSet>()));
        legoKitDao.addLegoKit(createLegoKit("Model Truck", new BigDecimal(43), 15, new HashSet<Category>(), new ArrayList<LegoPiece>(), new ArrayList<LegoSet>()));
        
        Assert.assertEquals(legoKits.size(), sizeBefore + 3);
    }

    @Test
    public void findLegoKitByIdTest() {
        LegoKit legoKit1 = createLegoKit("Pokemon Charizard", new BigDecimal(42), Integer.MAX_VALUE, new HashSet<Category>(), new ArrayList<LegoPiece>(), new ArrayList<LegoSet>());
        legoKitDao.addLegoKit(legoKit1);
        Long id = legoKit1.getId();
        
        LegoKit legoKit = em.createQuery("SELECT * FROM LegoKit l where id =  ?", LegoKit.class)
                .setParameter(1, id)
                .getSingleResult();

        Assert.assertEquals(legoKit.getName(), "Pokemon Charizard");
    }

    @Test
    public void updateLegoKitTest() {
        LegoKit legoKit1 = createLegoKit("Pokemon Charizard", new BigDecimal(42), Integer.MAX_VALUE, new HashSet<Category>(), new ArrayList<LegoPiece>(), new ArrayList<LegoSet>());
        legoKitDao.addLegoKit(legoKit1);
        
        //OK
        try {
            legoKitDao.updateLegoKit(legoKit1);
        } catch (Exception ex) {
            fail("Update throws exception " + ex);
        }
        
        legoKit1.setName("Model Truck");
        try{
            legoKitDao.updateLegoKit(legoKit1);
            LegoKit updated = legoKitDao.findLegoKitById(legoKit1.getId());
            assertEquals(updated.getName(), "Model Truck");
        } catch (Exception ex){
            fail("Updating name throws exception " + ex);
        }
        legoKit1.setPrice(BigDecimal.TEN);
        try{
            legoKitDao.updateLegoKit(legoKit1);
            LegoKit updated = legoKitDao.findLegoKitById(legoKit1.getId());
            assertEquals(updated.getPrice(), BigDecimal.TEN);
        } catch (Exception ex){
            fail("Updating price throws exception " + ex);
        }
        legoKit1.setAgeRestriction(42);
        try{
            legoKitDao.updateLegoKit(legoKit1);
            LegoKit updated = legoKitDao.findLegoKitById(legoKit1.getId());
            assertEquals(updated.getAgeRestriction(), 42);
        } catch (Exception ex){
            fail("Updating age restriction throws exception " + ex);
        }
        //null values
        try {
            legoKitDao.updateLegoKit(null);
            fail("Updating name do not throws exception");
        } catch (Exception ex) {
        }
        legoKit1.setName(null);
        try{
            legoKitDao.updateLegoKit(legoKit1);
            fail("Updating name do not throws exception");
        } catch (Exception ex){
        }
        legoKit1.setPrice(null);
        try{
            legoKitDao.updateLegoKit(legoKit1);
            fail("Updating price do not throws exception");
        } catch (Exception ex){
        }
        legoKit1.setCategories(null);
        try{
            legoKitDao.updateLegoKit(legoKit1);
            fail("Updating categories do not throws exception");
        } catch (Exception ex){
        }
        legoKit1.setLegoPieces(null);
        try{
            legoKitDao.updateLegoKit(legoKit1);
            fail("Updating pieces do not throws exception");
        } catch (Exception ex){
        }
        legoKit1.setLegoSets(null);
        try{
            legoKitDao.updateLegoKit(legoKit1);
            fail("Updating sets do not throws exception");
        } catch (Exception ex){
        }      
    }

    @Test
    public void deleteLegoKitTest() {
        LegoKit legoKit1 = createLegoKit("Pokemon Charizard", new BigDecimal(42), Integer.MAX_VALUE, new HashSet<Category>(), new ArrayList<LegoPiece>(), new ArrayList<LegoSet>());
        legoKitDao.addLegoKit(legoKit1);
        LegoKit stored = legoKitDao.findLegoKitById(legoKit1.getId());
        
        
        List<LegoKit> legoKits = em.createQuery("SELECT * FROM LegoKit l", LegoKit.class).getResultList();
        int sizeBefore = legoKits.size();
        //OK
        try {
            legoKitDao.deleteLegoKit(stored);
        } catch (Exception ex) {
            fail ("Exception thrown: " + ex.getMessage());
        }
        legoKits = em.createQuery("SELECT * FROM LegoKit l", LegoKit.class).getResultList();
        int sizeAfter = legoKits.size();
        
        assertEquals(sizeBefore - 1, sizeAfter);
        
        //with null
        try {
            legoKitDao.deleteLegoKit(null);
            fail("Deleting null must throw excetion");
        } catch (Exception ex) {
        }
        
        LegoKit legoKit2 = createLegoKit("StarWars Death Star", new BigDecimal(42), 12, new HashSet<Category>(), new ArrayList<LegoPiece>(), new ArrayList<LegoSet>());
        legoKitDao.addLegoKit(legoKit2);
        LegoKit stored2 = legoKitDao.findLegoKitById(legoKit2.getId());      
        
        try {
            legoKitDao.deleteLegoKit(stored2);
            legoKitDao.deleteLegoKit(stored2);
            fail ("Deleting unknown kit must throw exception");
        } catch (Exception ex) {
        }
    }
    
    private void assertDeepEquals(LegoKit piece1, LegoKit piece2) {
        assertEquals(piece1.getId(), piece2.getId());
        assertEquals(piece1.getName(), piece2.getName());
        assertEquals(piece1.getPrice(), piece2.getPrice());
        assertEquals(piece1.getAgeRestriction(), piece2.getAgeRestriction());
        assertEquals(piece1.getCategories(), piece2.getCategories());
        assertEquals(piece1.getLegoPieces(), piece2.getLegoPieces());
        assertEquals(piece1.getLegoSets(), piece2.getLegoSets());
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
