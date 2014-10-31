/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.legomanager.services.impl;

import cz.muni.fi.pa165.legomanager.LegoDaoException;
import cz.muni.fi.pa165.legomanager.LegoKitDao;
import cz.muni.fi.pa165.legomanager.entity.LegoKit;
import cz.muni.fi.pa165.legomanager.services.LegoKitService;
import cz.muni.fi.pa165.legomanager.transferobjects.LegoKitTO;
import java.util.ArrayList;
import java.util.List;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Petr Konecny
 */

@Service
@Transactional
public class LegoKitServiceImpl implements LegoKitService{
    
    @Autowired
    LegoKitDao legoKitDao;
    
    @Autowired
    DozerBeanMapper mapper;
    
            
    @Override
    public List<LegoKitTO> getAllLegoKits() {
        
        List<LegoKit> legoKits;
        try{
         legoKits = legoKitDao.getAllLegoKits();
        }catch(IllegalArgumentException | LegoDaoException ex){
            throw new RecoverableDataAccessException("error while retrieving data",ex) ;
        }
        List<LegoKitTO> legoKitsTO = new ArrayList<>();
        for(LegoKit kit : legoKits){
            legoKitsTO.add(mapper.map(kit, LegoKitTO.class));
        }
        return legoKitsTO;       
    }

    @Override
    public void updateLegoKit(LegoKitTO legoKit) {
        
        LegoKit kit  = mapper.map(legoKit, LegoKit.class);
        try{
            legoKitDao.updateLegoKit(kit);
        }catch (IllegalArgumentException | LegoDaoException ex){
            throw new RecoverableDataAccessException("error while retrieving data",ex) ;
        }
    }

    @Override
    public void deleteLegoKit(LegoKitTO legoKit) {
    
        LegoKit kit = mapper.map(legoKit, LegoKit.class);
        try{
        legoKitDao.deleteLegoKit(kit);
        }catch (IllegalArgumentException | LegoDaoException ex){
            throw new RecoverableDataAccessException("error while retrieving data",ex) ;
        }
    }

    @Override
    public void createLegoKit(LegoKitTO legoKit) {
        
        LegoKit kit = mapper.map(legoKit, LegoKit.class);
        try{
        legoKitDao.addLegoKit(kit);
        }catch (IllegalArgumentException | LegoDaoException ex){
            throw new RecoverableDataAccessException("error while retrieving data",ex) ;
        }
    }

    @Override
    public LegoKitTO getLegoKit(Long id) {
        
        LegoKit kit;
        try{
           kit = legoKitDao.findLegoKitById(id);
        }catch(IllegalArgumentException | LegoDaoException ex){
            throw new RecoverableDataAccessException("error while retrieving data",ex) ;
        }
        
        return mapper.map(kit,LegoKitTO.class);        
    }

    public void setLegoKitDao(LegoKitDao legoKitDao) {
        this.legoKitDao = legoKitDao;
    }

    public void setMapper(DozerBeanMapper mapper) {
        this.mapper = mapper;
    }
    
}
