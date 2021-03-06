/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.legomanager.services;

import cz.muni.fi.pa165.legomanager.dao.LegoPieceDao;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Martin Laštovička
 */
public class LegoPieceServiceImplTest extends BaseServiceTest {

    @InjectMocks
    @Autowired
    private LegoPieceService legoPieceService;

    @Mock
    private LegoPieceDao legoPieceDao;

    @Autowired
    private DozerBeanMapper mapper;

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
    public void addLegoPieceTest() {
        try {
            legoPieceService.createLegoPiece(legoPiece1);
            verify(legoPieceDao).addLegoPiece(mapper.map(legoPiece1, LegoPiece.class));
        } catch (Exception ex) {
            fail("Exception thrown: " + ex.getMessage());
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
    public void removeLegoPieceTest() {
        try {
            legoPieceService.removeLegoPiece(legoPiece1);
            verify(legoPieceDao).deleteLegoPiece(mapper.map(legoPiece1, LegoPiece.class));
        } catch (Exception ex) {
            fail("Exception thrown: " + ex.getMessage());
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
