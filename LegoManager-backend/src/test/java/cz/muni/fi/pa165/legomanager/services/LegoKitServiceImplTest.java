/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.legomanager.services;

import cz.muni.fi.pa165.legomanager.dao.LegoKitDao;
import cz.muni.fi.pa165.legomanager.entity.LegoKit;
import cz.muni.fi.pa165.legomanager.services.impl.LegoKitServiceImpl;
import cz.muni.fi.pa165.legomanager.transferobjects.CategoryTO;
import cz.muni.fi.pa165.legomanager.transferobjects.LegoKitTO;
import cz.muni.fi.pa165.legomanager.transferobjects.LegoPieceTO;
import cz.muni.fi.pa165.legomanager.transferobjects.LegoSetTO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import javax.persistence.PersistenceException;
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
 * @author Tomas
 */
public class LegoKitServiceImplTest extends BaseServiceTest {
    @Autowired
    @InjectMocks
    private LegoKitService legoKitService;

    @Mock
    private LegoKitDao legoKitDao;

    @Autowired
    private DozerBeanMapper mapper;

    private LegoKitTO legoKit;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        legoKit = createLegoKit(BigDecimal.TEN, 16, "Star Wars: Death Star");
        legoKit.setId(1L);

    }

    @Test
    public void getLegoKitTest() {
        try {
            when(legoKitDao.findLegoKitById(legoKit.getId()))
                    .thenReturn(mapper.map(legoKit, LegoKit.class));
            LegoKitTO kit = legoKitService.getLegoKit(legoKit.getId());
            verify(legoKitDao).findLegoKitById(legoKit.getId());
            
            assertDeepEquals(legoKit, kit);
            
        } catch (Exception ex) {
            fail("Exception thrown: " + ex.getMessage());
        }
    }

    @Test
    public void addLegoKitTest() {
        try {
            legoKitService.createLegoKit(legoKit);
            verify(legoKitDao).addLegoKit(mapper.map(legoKit, LegoKit.class));
        } catch (Exception ex) {
            fail("Exception thrown: " + ex.getMessage());
        }
    }

    
    @Test (expected = IllegalArgumentException.class)
    public void addLegoKitNullTestException() {
        doThrow(Exception.class).when(legoKitDao).addLegoKit(null);
        legoKitService.createLegoKit(null);
    }
    
    @Test
    public void updateLegoKitTest() {
        try {
            legoKitService.updateLegoKit(legoKit);
            verify(legoKitDao).updateLegoKit(mapper.map(legoKit, LegoKit.class));
        } catch (Exception ex) {
            fail("Exception thrown: " + ex.getMessage());
        }
    }

    @Test
    public void deleteLegoKitTest() {
        try {
            legoKitService.deleteLegoKit(legoKit);
            verify(legoKitDao).deleteLegoKit(mapper.map(legoKit, LegoKit.class));
        } catch (Exception ex) {
            fail("Exception thrown: " + ex.getMessage());
        }
    }
    
    @Test
    public void getAllLegoKitsTest() {
        try {
            legoKitService.getAllLegoKits();
            verify(legoKitDao).getAllLegoKits();
        } catch (Exception ex) {
            fail("Exception thrown: " + ex.getMessage());
        }
    }
    
    private LegoKitTO createLegoKit(BigDecimal price, Integer ageRestriction, String name) {
        LegoKitTO newKit = new LegoKitTO();
        newKit.setPrice(price);
        newKit.setAgeRestriction(ageRestriction);
        newKit.setName(name);
        newKit.setCategories(new HashSet<CategoryTO>());
        newKit.setLegoPieces(new ArrayList<LegoPieceTO>());
        newKit.setLegoSets(new ArrayList<LegoSetTO>());

        return newKit;
    }

    private void assertDeepEquals(LegoKitTO piece1, LegoKitTO piece2) {
        assertEquals(piece1.getId(), piece2.getId());
        assertEquals(piece1.getPrice(), piece2.getPrice());
        assertEquals(piece1.getAgeRestriction(), piece2.getAgeRestriction());
        assertEquals(piece1.getName(), piece2.getName());
        assertEquals(piece1.getCategories(), piece2.getCategories());
        assertEquals(piece1.getLegoPieces(), piece2.getLegoPieces());
        assertEquals(piece1.getLegoSets(), piece2.getLegoSets());
    }
}
