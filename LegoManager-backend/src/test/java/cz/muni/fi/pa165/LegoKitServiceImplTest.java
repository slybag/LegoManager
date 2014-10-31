/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165;

import cz.muni.fi.pa165.legomanager.LegoKitDao;
import cz.muni.fi.pa165.legomanager.services.impl.LegoKitServiceImpl;
import cz.muni.fi.pa165.legomanager.transferobjects.LegoKitTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doThrow;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Petr
 */
public class LegoKitServiceImplTest extends BaseTest {
    
    @InjectMocks
    @Autowired
    private LegoKitServiceImpl kitService;
    
    @Mock
    private LegoKitDao legoKitDao;
    
    @Before
    public void setUpMock(){
        MockitoAnnotations.initMocks(this);
        
    }
    
    @Test
    public void testCreate(){
        LegoKitTO legoKitTO = new LegoKitTO();
        legoKitTO.setName("Star Wars");
        legoKitTO.setAgeRestriction(-1);
        kitService.createLegoKit(legoKitTO);
    }

    public void setKitService(LegoKitServiceImpl kitService) {
        this.kitService = kitService;
    }

    public void setLegoKitDao(LegoKitDao legoKitDao) {
        this.legoKitDao = legoKitDao;
    }
    
    
}
