/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import cz.muni.fi.pa165.BaseTest;
import cz.muni.fi.pa165.legomanager.LegoDaoException;
import cz.muni.fi.pa165.legomanager.LegoPieceDao;
import cz.muni.fi.pa165.legomanager.entity.LegoPiece;
import cz.muni.fi.pa165.legomanager.services.impl.LegoPieceServiceImpl;
import cz.muni.fi.pa165.legomanager.support.Color.PieceColor;
import cz.muni.fi.pa165.legomanager.transferobjects.LegoKitTO;
import cz.muni.fi.pa165.legomanager.transferobjects.LegoPieceTO;
import java.util.ArrayList;
import org.dozer.DozerBeanMapper;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
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

/**
 *
 * @author Martin Laštovička
 */
public class LegoPieceServiceImplTest extends BaseServiceTest {

    @InjectMocks
    @Autowired
    private LegoPieceServiceImpl legoPieceService;

    @Mock
    private LegoPieceDao legoPieceDao;

    @Autowired
    DozerBeanMapper mapper;

    private LegoPieceTO legoPiece1;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        legoPiece1 = createLegoPiece(PieceColor.BLACK);
        legoPiece1.setId(1L);

    }

    @Test
    public void getLegoPieceTest() {
        try {
            when(legoPieceDao.findLegoPieceById(legoPiece1.getId()))
                    .thenReturn(mapper.map(legoPiece1, LegoPiece.class));
            LegoPieceTO piece = legoPieceService.getLegoPiece(legoPiece1.getId());
            verify(legoPieceDao).findLegoPieceById(legoPiece1.getId());
            
            assertDeepEquals(legoPiece1, piece);
            
        } catch (Exception ex) {
            fail("Exception thrown: " + ex.getMessage());
        }
    }

    @Test
    public void getLegoPieceTestException() {
        try {
            doThrow(LegoDaoException.class).when(legoPieceDao).findLegoPieceById(legoPiece1.getId());

            legoPieceService.getLegoPiece(legoPiece1.getId());
            fail("No exception thrown.");
        } catch (DataAccessException ex) {
            // OK
        } catch (Exception ex) {
            fail("Bad exception throwed: " + ex);
        }
    }

    @Test
    public void addLegoPieceTest() {
        try {
            legoPieceService.createLegoPiece(legoPiece1);
            verify(legoPieceDao).addLegoPiece(mapper.map(legoPiece1, LegoPiece.class));
        } catch (Exception ex) {
            fail("Exception thrown: " + ex.getMessage());
        }
    }

    @Test
    public void addLegoPieceTestException() {
        try {
            doThrow(LegoDaoException.class).when(legoPieceDao).addLegoPiece(null);

            legoPieceService.createLegoPiece(null);
            fail("No exception thrown.");
        } catch (DataAccessException ex) {
            // OK
        } catch (Exception ex) {
            fail("Bad exception throwed: " + ex);
        }

        try {
            doThrow(DataAccessException.class).when(legoPieceDao).addLegoPiece(mapper.map(createLegoPiece(null), LegoPiece.class));

            legoPieceService.createLegoPiece(null);
            fail("No exception thrown.");
        } catch (DataAccessException ex) {
            // OK
        } catch (Exception ex) {
            fail("Bad exception throwed: " + ex);
        }
    }

    @Test
    public void updateLegoPieceTest() {
        try {
            legoPieceService.updateLegoPiece(legoPiece1);
            verify(legoPieceDao).updateLegoPiece(mapper.map(legoPiece1, LegoPiece.class));
        } catch (Exception ex) {
            fail("Exception thrown: " + ex.getMessage());
        }
    }

    @Test
    public void updateLegoPieceTestException() {
        try {
            doThrow(LegoDaoException.class).when(legoPieceDao)
                    .updateLegoPiece(mapper.map(legoPiece1, LegoPiece.class));

            legoPieceService.updateLegoPiece(legoPiece1);
            fail("No exception thrown.");
        } catch (DataAccessException ex) {
            // OK
        } catch (Exception ex) {
            fail("Bad exception throwed: " + ex);
        }
    }

    @Test
    public void removeLegoPieceTest() {
        try {
            legoPieceService.removeLegoPiece(legoPiece1);
            verify(legoPieceDao).deleteLegoPiece(mapper.map(legoPiece1, LegoPiece.class));
        } catch (Exception ex) {
            fail("Exception thrown: " + ex.getMessage());
        }
    }

    @Test
    public void removeLegoPieceTestException() {
        try {
            doThrow(LegoDaoException.class).when(legoPieceDao)
                    .deleteLegoPiece(mapper.map(legoPiece1, LegoPiece.class));

            legoPieceService.removeLegoPiece(legoPiece1);
            fail("No exception thrown.");
        } catch (DataAccessException ex) {
            // OK
        } catch (Exception ex) {
            fail("Bad exception throwed: " + ex);
        }
    }
    
    @Test
    public void getAllLegoPiecesTest() {
        try {
            legoPieceService.getAllLegoPieces();
            verify(legoPieceDao).getAllLegoPieces();
        } catch (Exception ex) {
            fail("Exception thrown: " + ex.getMessage());
        }
    }

    @Test
    public void getAllLegoPiecesTestException() {
        try {
            doThrow(LegoDaoException.class).when(legoPieceDao).getAllLegoPieces();

            legoPieceService.getAllLegoPieces();
            fail("No exception thrown.");
        } catch (DataAccessException ex) {
            // OK
        } catch (Exception ex) {
            fail("Bad exception throwed: " + ex);
        }
    }
    
    private LegoPieceTO createLegoPiece(PieceColor pieceColor) {
        LegoPieceTO newPiece = new LegoPieceTO();
        newPiece.setColor(pieceColor);
        newPiece.setLegoKits(new ArrayList<LegoKitTO>());

        return newPiece;
    }

    private void assertDeepEquals(LegoPieceTO piece1, LegoPieceTO piece2) {
        assertEquals(piece1.getId(), piece2.getId());
        assertEquals(piece1.getColor(), piece2.getColor());
        assertEquals(piece1.getLegoKits(), piece2.getLegoKits());
    }
}
