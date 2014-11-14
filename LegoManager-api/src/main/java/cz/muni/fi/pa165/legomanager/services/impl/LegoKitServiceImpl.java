/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.legomanager.services.impl;

import cz.muni.fi.pa165.legomanager.dao.LegoKitDao;
import cz.muni.fi.pa165.legomanager.entity.LegoKit;
import cz.muni.fi.pa165.legomanager.services.LegoKitService;
import cz.muni.fi.pa165.legomanager.transferobjects.LegoKitTO;
import java.util.ArrayList;
import java.util.List;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Petr Konecny
 */
@Service
@Transactional
public class LegoKitServiceImpl implements LegoKitService {

    @Autowired
    LegoKitDao legoKitDao;
    
    @Autowired
    DozerBeanMapper mapper;

    @Override
    public List<LegoKitTO> getAllLegoKits() {
        
        List<LegoKitTO> legoKitsTO = new ArrayList<>();
        for (LegoKit kit : legoKitDao.getAllLegoKits()) {
            legoKitsTO.add(mapper.map(kit, LegoKitTO.class));
        }       
        return legoKitsTO;
    }

    @Override
    public void updateLegoKit(LegoKitTO legoKit) {      
        if(legoKit==null) throw new IllegalArgumentException();
        legoKitDao.updateLegoKit(mapper.map(legoKit, LegoKit.class));
        
    }

    @Override
    public void deleteLegoKit(LegoKitTO legoKit) {
        if(legoKit==null) throw new IllegalArgumentException();
        legoKitDao.deleteLegoKit(mapper.map(legoKit, LegoKit.class));
       
    }

    @Override
    public void createLegoKit(LegoKitTO legoKit) {        
        if(legoKit==null) throw new IllegalArgumentException();
        legoKitDao.addLegoKit(mapper.map(legoKit, LegoKit.class));
       
    }

    @Override
    public LegoKitTO getLegoKit(Long id) {
        if(id==null) throw new IllegalArgumentException();
        return mapper.map(legoKitDao.findLegoKitById(id), LegoKitTO.class);       
    }

    public void setLegoKitDao(LegoKitDao legoKitDao) {
        this.legoKitDao = legoKitDao;
    }

    public void setMapper(DozerBeanMapper mapper) {
        this.mapper = mapper;
    }
}
