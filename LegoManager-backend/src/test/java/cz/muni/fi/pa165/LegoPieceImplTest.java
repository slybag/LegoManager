/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165;

import cz.muni.fi.pa165.legomanager.LegoPieceDao;
import cz.muni.fi.pa165.legomanager.entity.LegoKit;
import cz.muni.fi.pa165.legomanager.entity.LegoPiece;
import cz.muni.fi.pa165.legomanager.support.Color.PieceColor;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Martin Laštovička
 */
public class LegoPieceImplTest extends BaseTest {

    @Autowired
    LegoPieceDao legoPieceDao;

    @PersistenceContext
    EntityManager em;

    @Test
    public void addLegoPieceTest() {
        LegoPiece legoPiece1 = createLegoPiece(PieceColor.RED);
        try {
            legoPieceDao.addLegoPiece(legoPiece1);
        } catch (Exception e) {
            fail("Exception thrown" + e.getMessage());
        }

        LegoPiece piece = legoPieceDao.findLegoPieceById(legoPiece1.getId());
        assertDeepEquals(piece, legoPiece1);
    }
    @Test
    public void addLegoPieceTestNullPiece() {
        try {
            legoPieceDao.addLegoPiece(null);
            fail("No exception thrown");
        } catch (Exception ex) {
        }
    }
    @Test
    public void addLegoPieceTestNullColor() {
        LegoPiece legoPieceNull = createLegoPiece(null);
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

        LegoPiece piece1 = createLegoPiece(PieceColor.RED);
        LegoPiece piece2 = createLegoPiece(PieceColor.GREEN);
        LegoPiece piece3 = createLegoPiece(PieceColor.BLUE);
        legoPieceDao.addLegoPiece(piece1);
        legoPieceDao.addLegoPiece(piece2);
        legoPieceDao.addLegoPiece(piece3);       

        legoPieces = em.createQuery("SELECT l FROM LegoPiece l", LegoPiece.class).getResultList();
        int sizeAfter = legoPieces.size();
        Assert.assertEquals(sizeBefore + 3, sizeAfter);
        
        assertDeepEquals(legoPieces.get(sizeAfter-3), piece1);
        assertDeepEquals(legoPieces.get(sizeAfter-2), piece2);
        assertDeepEquals(legoPieces.get(sizeAfter-1), piece3);
    }

    @Test
    public void findLegoPieceByIdTest() {
        LegoPiece legoPiece1 = createLegoPiece(PieceColor.RED);
        legoPieceDao.addLegoPiece(legoPiece1);
        Long id = legoPiece1.getId();
        
        LegoPiece legoPiece = em.find(LegoPiece.class, id);
        assertDeepEquals(legoPiece1, legoPiece);
    }

    @Test
    public void updateLegoPieceTest() {
        LegoPiece legoPiece1 = createLegoPiece(PieceColor.RED);
        legoPieceDao.addLegoPiece(legoPiece1);
        
        //OK
        try {
            legoPieceDao.updateLegoPiece(legoPiece1);
        } catch (Exception ex) {
            fail("No exception");
        }
        
        legoPiece1.setColor(PieceColor.PURPLE);
        try{
            legoPieceDao.updateLegoPiece(legoPiece1);
            LegoPiece updated = legoPieceDao.findLegoPieceById(legoPiece1.getId());
            assertEquals(updated.getColor(), PieceColor.PURPLE);
        } catch (Exception ex){
            fail("No exception");
        }
    }
    
    @Test
    public void updateLegoPieceTestNullPiece() {
        try {
            legoPieceDao.updateLegoPiece(null);
            fail ("No exception");
        } catch (Exception ex) {
        }        
    }

    @Test
    public void deleteLegoPieceTest() {
        LegoPiece legoPiece1 = createLegoPiece(PieceColor.RED);
        legoPieceDao.addLegoPiece(legoPiece1);
        LegoPiece stored = legoPieceDao.findLegoPieceById(legoPiece1.getId());
        
        //OK
        try {
            legoPieceDao.deleteLegoPiece(stored);
        } catch (Exception ex) {
            fail ("Exception thrown: " + ex.getMessage());
        }
        
        // check if its still there
        try {            
            legoPieceDao.findLegoPieceById(stored.getId());
            fail ("No Exception thrown: ");
        } catch (Exception ex){            
        }
    }
    @Test
    public void deleteLegoPieceTestNullPiece() {
        //with null
        try {
            legoPieceDao.deleteLegoPiece(null);
            fail ("No exception thrown");
        } catch (Exception ex) {
        }
    }
    
    @Test
    public void deleteLegoPieceTestPieceNotInDB() {
        //not in DB
        LegoPiece legoPiece1 = createLegoPiece(PieceColor.RED);
        legoPieceDao.addLegoPiece(legoPiece1); 
        
        try {
            legoPieceDao.deleteLegoPiece(legoPiece1);
            legoPieceDao.deleteLegoPiece(legoPiece1);
            fail ("No exception thrown");
        } catch (Exception ex) {
        }
    }
    
    private void assertDeepEquals(LegoPiece piece1, LegoPiece piece2) {
        assertEquals(piece1.getId(), piece2.getId());
        assertEquals(piece1.getColor(), piece2.getColor());
        assertEquals(piece1.getLegoKits(), piece2.getLegoKits());
    }

    private LegoPiece createLegoPiece(PieceColor color) {
        LegoPiece newPiece = new LegoPiece();
        newPiece.setColor(color);
        newPiece.setLegoKits(new ArrayList<LegoKit>());
        
        return newPiece;
    }
}
