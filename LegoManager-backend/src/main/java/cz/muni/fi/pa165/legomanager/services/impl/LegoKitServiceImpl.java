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
import org.dozer.MappingException;
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
public class LegoKitServiceImpl implements LegoKitService {

    @Autowired
    LegoKitDao legoKitDao;
    @Autowired
    DozerBeanMapper mapper;

    @Override
    public List<LegoKitTO> getAllLegoKits() {
        
        List<LegoKitTO> legoKitsTO = new ArrayList<>();
        try {
            for (LegoKit kit : legoKitDao.getAllLegoKits()) {
                legoKitsTO.add(mapper.map(kit, LegoKitTO.class));
            }
        } catch (IllegalArgumentException | LegoDaoException | MappingException ex) {
            throw new RecoverableDataAccessException("error while retrieving data", ex);
        }
        return legoKitsTO;
    }

    @Override
    public void updateLegoKit(LegoKitTO legoKit) {
        
        try {
            legoKitDao.updateLegoKit(mapper.map(legoKit, LegoKit.class));
        } catch (MappingException | IllegalArgumentException | LegoDaoException ex) {
            throw new RecoverableDataAccessException("error while updating lego kit", ex);
        }
    }

    @Override
    public void deleteLegoKit(LegoKitTO legoKit) {

        try {
            legoKitDao.deleteLegoKit(mapper.map(legoKit, LegoKit.class));
        } catch (MappingException | IllegalArgumentException | LegoDaoException ex) {
            throw new RecoverableDataAccessException("error while deleting lego kit", ex);
        }
    }

    @Override
    public void createLegoKit(LegoKitTO legoKit) {

        try {
            legoKitDao.addLegoKit(mapper.map(legoKit, LegoKit.class));
        } catch (MappingException | IllegalArgumentException | LegoDaoException ex) {
            throw new RecoverableDataAccessException("error while creating lego kit", ex);
        }
    }

    @Override
    public LegoKitTO getLegoKit(Long id) {
        
        LegoKitTO legoKit;
        try {
            legoKit = mapper.map(legoKitDao.findLegoKitById(id), LegoKitTO.class);
        } catch (IllegalArgumentException | LegoDaoException | MappingException ex) {
            throw new RecoverableDataAccessException("error while retrieving lego kit", ex);
        }
        return legoKit;
    }

    public void setLegoKitDao(LegoKitDao legoKitDao) {
        this.legoKitDao = legoKitDao;
    }

    public void setMapper(DozerBeanMapper mapper) {
        this.mapper = mapper;
    }
}
