/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.legomanager.dao.impl;

import cz.muni.fi.pa165.legomanager.dao.LegoKitDao;
import cz.muni.fi.pa165.legomanager.entity.LegoKit;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import org.springframework.stereotype.Repository;


/**
 *
 * @author Petr Konecny
 */

@Repository
public class LegoKitDaoImpl implements LegoKitDao{
    
    List<LegoKit> kits;
    
    @PersistenceContext
    EntityManager em;
    
    @Override
    public List<LegoKit> getAllLegoKits() {
        kits =  em.createQuery("Select k from LegoKit k",LegoKit.class).getResultList();
        return kits;
    }
    
    @Override
    public void updateLegoKit(LegoKit kit) {
        if (!isValidLegoKit(kit)) {
            throw new IllegalArgumentException("Not a valid lego kit");
        } else if(em.find(LegoKit.class, kit.getId()) == null) {
            throw new PersistenceException("Kit is not in database");
        }     
        em.merge(kit);
    }

    @Override
    public void deleteLegoKit(LegoKit kit) {
        if (!isValidLegoKit(kit)) {
            throw new IllegalArgumentException("Not a valid lego kit");
        } else if(em.find(LegoKit.class, kit.getId()) == null) {
            throw new PersistenceException("Kit is not in database");
        }
        em.remove(kit);
   }

    @Override
    public void addLegoKit(LegoKit kit) {
        if (!isValidLegoKit(kit)) {
            throw new IllegalArgumentException("Not a valid lego kit");
        }
        em.persist(kit);
    }

    @Override
    public LegoKit findLegoKitById(Long id) {
        
        if(id == null) {
            throw new IllegalArgumentException("Id parameter is null.");
        }     
        return em.find(LegoKit.class, id);
    }   
    
     private boolean isValidLegoKit(LegoKit legoKit) { 
        if (legoKit == null ||
                legoKit.getCategories() == null || 
                legoKit.getLegoPieces() == null ||
                legoKit.getLegoSets()== null ||
                legoKit.getName() == null || 
                legoKit.getPrice() == null ||
                legoKit.getAgeRestriction() == null ||
                legoKit.getAgeRestriction() < 0 ||
                legoKit.getPrice().compareTo(BigDecimal.ZERO) == -1 ) return false;        
        return true;
    }
}
