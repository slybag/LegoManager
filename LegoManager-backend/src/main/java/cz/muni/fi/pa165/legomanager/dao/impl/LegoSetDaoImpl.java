/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.legomanager.dao.impl;

import cz.muni.fi.pa165.legomanager.dao.LegoDaoException;
import cz.muni.fi.pa165.legomanager.dao.LegoSetDao;
import cz.muni.fi.pa165.legomanager.entity.LegoSet;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Michal 
 */
@Repository
public class LegoSetDaoImpl implements LegoSetDao {
    
    @PersistenceContext
    EntityManager entityManager;
    
    @Override
    public List<LegoSet> getAllLegoSets() throws IllegalArgumentException, LegoDaoException{
        return entityManager.createQuery("SELECT s FROM LegoSet s", LegoSet.class).getResultList();
    }

    @Override
    public void updateLegoSet(LegoSet legoSet) throws IllegalArgumentException {
        if (legoSet == null || !isValidLegoSet(legoSet)) throw new IllegalArgumentException();
        entityManager.merge(legoSet);
    }

    @Override
    public void deleteLegoSet(LegoSet legoSet) throws IllegalArgumentException, LegoDaoException {
        if (legoSet == null) throw new IllegalArgumentException();
        if (legoSet.getId() == null) throw new LegoDaoException();
        
        LegoSet setToDelete = entityManager.merge(legoSet);
        
        entityManager.remove(setToDelete);
    }

    @Override
    public void addLegoSet(LegoSet legoSet) throws IllegalArgumentException {
        if (legoSet == null || !isValidLegoSet(legoSet)) throw new IllegalArgumentException();
        entityManager.persist(legoSet);
    }

    @Override
    public LegoSet findLegoSetById(Long id) throws LegoDaoException {
        LegoSet setToFind = entityManager.find(LegoSet.class, id);
        if (setToFind == null) throw new LegoDaoException("Lego Set with id: " + id + " is not in DB");
        
        return setToFind;
    }
    
    //prepared function
    private boolean isValidLegoSet(LegoSet legoSet) {                        
        if (legoSet.getCategories() == null || 
                legoSet.getLegoKits() == null || 
                legoSet.getName() == null || 
                legoSet.getPrice() == null ||
                legoSet.getPrice().intValue() < 0) return false;        
        return true;
    }
}
