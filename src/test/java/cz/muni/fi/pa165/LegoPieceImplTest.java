/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165;

import cz.muni.fi.pa165.legomanager.CategoryDao;
import cz.muni.fi.pa165.legomanager.LegoPieceDao;
import cz.muni.fi.pa165.legomanager.entity.LegoPiece;
import java.util.List;
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
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

/**
 *
 * @author Martin Laštovička
 */
public class LegoPieceImplTest extends BaseTest {

    @Autowired
    LegoPieceDao legoPieceDao;

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
    public void addLegoPieceTest() {
        LegoPiece legoPiece1 = createLegoPiece("Red");
        try {
            legoPieceDao.addLegoPiece(legoPiece1);
        } catch (Exception e) {
            fail("Exception thrown" + e.getMessage());
        }

        LegoPiece piece = legoPieceDao.findLegoPieceById(legoPiece1.getId());
        assertDeepEquals(piece, legoPiece1);

        //with null
        try {
            legoPieceDao.addLegoPiece(null);
            fail("No exception thrown");
        } catch (Exception ex) {
        }

        LegoPiece legoPieceNull = createLegoPiece(null);
        //with null params
        try {
            legoPieceDao.addLegoPiece(legoPieceNull);
            fail("No exception thrown");
        } catch (Exception ex) {
        }
    }

    @Test
    public void getAllLegoPiecesTest() {
        List<LegoPiece> legoPieces = em.createQuery("SELECT l FROM LegoPiece l", LegoPiece.class).getResultList();
        int sizeBefore = legoPieces.size();
        legoPieceDao.addLegoPiece(createLegoPiece("Red"));
        legoPieceDao.addLegoPiece(createLegoPiece("Green"));
        legoPieceDao.addLegoPiece(createLegoPiece("Blue"));       
        
        Assert.assertEquals(legoPieces.size(), sizeBefore + 3);
    }

    @Test
    public void findLegoPieceByIdTest() {
        LegoPiece legoPiece1 = createLegoPiece("Red");
        legoPieceDao.addLegoPiece(legoPiece1);
        Long id = legoPiece1.getId();
        
        LegoPiece legoPiece = em.createQuery("SELECT l FROM LegoPiece l where id =  ?", LegoPiece.class)
                .setParameter(1, id)
                .getSingleResult();

        Assert.assertEquals(legoPiece.getColor(), "Red");
    }

    @Test
    public void updateLegoPieceTest() {
        LegoPiece legoPiece1 = createLegoPiece("Red");
        legoPieceDao.addLegoPiece(legoPiece1);
        
        //OK
        try {
            legoPieceDao.updateLegoPiece(legoPiece1);
        } catch (Exception ex) {
            fail("No exception");
        }
        
        legoPiece1.setColor("Black");
        try{
            legoPieceDao.updateLegoPiece(legoPiece1);
            LegoPiece updated = legoPieceDao.findLegoPieceById(legoPiece1.getId());
            assertEquals(updated.getColor(), "Black");
        } catch (Exception ex){
            fail("No exception");
        }
        
        
        //with null
        try {
            legoPieceDao.updateLegoPiece(null);
            fail ("No exception");
        } catch (Exception ex) {
        }        
    }

    @Test
    public void deleteLegoPieceTest() {
        LegoPiece legoPiece1 = createLegoPiece("Red");
        legoPieceDao.addLegoPiece(legoPiece1);
        LegoPiece stored = legoPieceDao.findLegoPieceById(legoPiece1.getId());
        
        
        List<LegoPiece> legoPieces = em.createQuery("SELECT l FROM LegoPiece l", LegoPiece.class).getResultList();
        int sizeBefore = legoPieces.size();
        //OK
        try {
            legoPieceDao.deleteLegoPiece(stored);
        } catch (Exception ex) {
            fail ("Exception thrown: " + ex.getMessage());
        }
        legoPieces = em.createQuery("SELECT l FROM LegoPiece l", LegoPiece.class).getResultList();
        int sizeAfter = legoPieces.size();
        
        assertEquals(sizeBefore - 1, sizeAfter);
        
        //with null
        try {
            legoPieceDao.deleteLegoPiece(null);
            fail ("No exception thrown");
        } catch (Exception ex) {
        }
        
        //not in DB
        LegoPiece legoPiece2 = createLegoPiece("Blue");
        legoPieceDao.addLegoPiece(legoPiece1);
        LegoPiece stored2 = legoPieceDao.findLegoPieceById(legoPiece1.getId());      
        
        try {
            legoPieceDao.deleteLegoPiece(stored2);
            legoPieceDao.deleteLegoPiece(stored2);
            fail ("No exception thrown");
        } catch (Exception ex) {
        }
    }
    
    private void assertDeepEquals(LegoPiece piece1, LegoPiece piece2) {
        assertEquals(piece1.getId(), piece2.getId());
        assertEquals(piece1.getColor(), piece2.getColor());
    }

    private LegoPiece createLegoPiece(String color) {
        LegoPiece newPiece = new LegoPiece();
        newPiece.setColor(color);
        
        return newPiece;
    }
}
