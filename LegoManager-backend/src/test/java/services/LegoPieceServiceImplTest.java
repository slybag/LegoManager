/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import cz.muni.fi.pa165.BaseTest;
import cz.muni.fi.pa165.legomanager.LegoDaoException;
import cz.muni.fi.pa165.legomanager.LegoPieceDao;
import cz.muni.fi.pa165.legomanager.entity.LegoKit;
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
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Martin Laštovička
 */
public class LegoPieceServiceImplTest extends BaseTest{
    
    @InjectMocks
    private LegoPieceServiceImpl legoPieceService;
    
    @Mock
    private LegoPieceDao legoPieceDao;
    
    @Autowired
    DozerBeanMapper mapper;
    
    private LegoPieceTO legoPiece1;
    
    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);  
        
        legoPiece1 = createLegoPiece(PieceColor.BLACK);
        legoPiece1.setId(1L);
        when(legoPieceDao.findLegoPieceById(legoPiece1.getId()))
                .thenReturn(mapper.map(legoPiece1, LegoPiece.class));
        
        
        //doThrow(DataAccessException.class).when(legoPieceDao).addLegoPiece(null);
        //doThrow(DataAccessException.class).when(legoPieceDao).addLegoPiece(mapper.map(createLegoPiece(null), LegoPiece.class));
    }
    
    @Test
    public void findLegoPieceByIdTest() {
        //legoPieceService.createLegoPiece(legoPiece1);
                
        LegoPieceTO legoPiece = legoPieceService.getLegoPiece(legoPiece1.getId());
        assertDeepEquals(legoPiece1, legoPiece);
    }
    
    @Test
    public void addLegoPieceTest() {
        try {
            legoPieceService.createLegoPiece(legoPiece1);
        } catch (Exception e) {
            fail("Exception thrown" + e.getMessage());
        }

        LegoPieceTO piece = legoPieceService.getLegoPiece(legoPiece1.getId());
        assertDeepEquals(piece, legoPiece1);
    }

    @Test
    public void addLegoPieceTestNullPiece() {
        try {
            legoPieceService.createLegoPiece(null);
            fail("No exception thrown");
        } catch(DataAccessException ex){
            // if you choose Spring for implementation make sure that DataAccessException 
            // or its subclass is thrown in case of any exception on a persistence layer
        }catch(Exception ex){
            fail("Bad exception throwed" + ex);
        }
    }
    
    @Test
    public void addLegoPieceTestNullColor() {
        LegoPieceTO legoPieceNullColor = createLegoPiece(null);
        try {
            legoPieceService.createLegoPiece(legoPieceNullColor);
            fail("No exception thrown");
        } catch(DataAccessException ex){
            // if you choose Spring for implementation make sure that DataAccessException 
            // or its subclass is thrown in case of any exception on a persistence layer
        }catch(Exception ex){
            fail("Bad exception throwed" + ex);
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
