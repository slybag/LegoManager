/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.legomanager.services.impl;

import cz.muni.fi.pa165.legomanager.dao.LegoSetDao;
import cz.muni.fi.pa165.legomanager.entity.LegoSet;
import cz.muni.fi.pa165.legomanager.services.LegoSetService;
import cz.muni.fi.pa165.legomanager.transferobjects.LegoSetTO;
import java.util.ArrayList;
import java.util.List;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Michal
 */
@Service
@Transactional
public class LegoSetServiceImpl implements LegoSetService {

    @Autowired
    LegoSetDao legoSetDao;

    @Autowired
    DozerBeanMapper mapper;

    public void setLegoSetDao(LegoSetDao legoSetDao) {
        this.legoSetDao = legoSetDao;
    }

    public void setMapper(DozerBeanMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public void createLegoSet(LegoSetTO legoSet) {
        if(legoSet==null) throw new IllegalArgumentException();
        legoSetDao.addLegoSet(mapper.map(legoSet, LegoSet.class));
               
    }

    @Override
    @Transactional
    public void updateLegoSet(LegoSetTO legoSet) {
        if(legoSet==null) throw new IllegalArgumentException();
        legoSetDao.updateLegoSet(mapper.map(legoSet, LegoSet.class));
        
    }

    @Override
    @Transactional
    public void removeLegoSet(LegoSetTO legoSet) {
        if(legoSet==null) throw new IllegalArgumentException();
        legoSetDao.deleteLegoSet(mapper.map(legoSet, LegoSet.class));
        
    }

    @Override
    @Transactional
    public LegoSetTO getLegoSet(Long id) {
        if(id == null) throw new IllegalArgumentException();
        return mapper.map(legoSetDao.findLegoSetById(id), LegoSetTO.class);      
    }

    @Override
    @Transactional
    public List<LegoSetTO> getAllLegoSets() {
        List<LegoSetTO> legoSetsTO = new ArrayList<>();
        List<LegoSet> legoSets = legoSetDao.getAllLegoSets();
        for (LegoSet set : legoSets) {
            legoSetsTO.add(mapper.map(set, LegoSetTO.class));
        }
        return legoSetsTO;
    }

}
