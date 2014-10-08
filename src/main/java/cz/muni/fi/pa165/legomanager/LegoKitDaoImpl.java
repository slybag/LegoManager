/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.legomanager;

import cz.muni.fi.pa165.legomanager.entity.Category;
import cz.muni.fi.pa165.legomanager.entity.LegoKit;
import cz.muni.fi.pa165.legomanager.entity.LegoPiece;
import cz.muni.fi.pa165.legomanager.entity.LegoSet;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


/**
 *
 * @author Petr
 */
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
        if (kit == null) {
            throw new IllegalArgumentException("Kit argument is null");
        } else if (kit.getName() == null) {
            throw new IllegalArgumentException("Kit name wasn't set");
        } else if (kit.getPrice() == null) {
            throw new IllegalArgumentException("Price argument is null");
        } else if (kit.getLegoPieces() == null){
            throw new IllegalArgumentException("Lego pieces argument is null");
        }else if (kit.getPrice().intValue() <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        } else if (kit.getAgeRestriction()<= 0) {
            throw new IllegalArgumentException("Age restriciton must be greater than 0");
        }else if(em.find(LegoKit.class, kit.getId()) == null) {
            throw new LegoDaoException("Kit is not in database");
        }     
        em.merge(kit);
    }

    @Override
    public void deleteLegoKit(LegoKit kit) {
        if (kit == null) {
            throw new IllegalArgumentException("Kit argument is null");
        }else if(kit.getId() == null){
            throw new IllegalArgumentException("Kit id was not specified");
        }else if(em.find(LegoKit.class, kit.getId()) == null) {
            throw new LegoDaoException("Kit is not in database");
        }
        em.remove(kit);
   }

    @Override
    public void addLegoKit(LegoKit kit) {
        if (kit == null) {
            throw new IllegalArgumentException("Kit argument is null");
        } else if (kit.getName() == null) {
            throw new IllegalArgumentException("Kit name wasn't set");
        } else if (kit.getPrice() == null) {
            throw new IllegalArgumentException("Price argument is null");
        } else if (kit.getLegoPieces() == null){
            throw new IllegalArgumentException("Lego pieces argument is null");
        } else if (kit.getPrice().intValue() <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        } else if (kit.getAgeRestriction()<= 0) {
            throw new IllegalArgumentException("Age restriciton must be greater than 0");
        }      
        em.persist(kit);
    }

    @Override
    public LegoKit findLegoKitById(Long id) {
        
        if(id == null) {
            throw new IllegalArgumentException("Id is null.");
        }
        LegoKit kitToReturn = em.find(LegoKit.class, id);       
        if(kitToReturn == null){
            throw new LegoDaoException("Kit is not in the database");
        }       
        return em.find(LegoKit.class, id);
    }   
}
