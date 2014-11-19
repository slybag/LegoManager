/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.legomanager.dao.impl;

import cz.muni.fi.pa165.legomanager.dao.LegoPieceDao;
import cz.muni.fi.pa165.legomanager.entity.LegoPiece;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Tomas
 */
@Repository
public class LegoPieceDaoImpl implements LegoPieceDao {
    @PersistenceContext
    EntityManager entityManager;
    
    @Override
    public List<LegoPiece> getAllLegoPieces(){
        return entityManager.createQuery("SELECT Q FROM LegoPiece Q", LegoPiece.class).getResultList();
    }

    @Override
    public void updateLegoPiece(LegoPiece legoPiece) {
        String error = isValidLegoPiece(legoPiece);
        if (error.length() != 0) throw new IllegalArgumentException(error);
        try{
            entityManager.merge(legoPiece);
        }catch(IllegalArgumentException ex){
            throw new PersistenceException(ex);
        }
    }

    @Override
    public void deleteLegoPiece(LegoPiece legoPiece) {
        LegoPiece setToFind = entityManager.find(LegoPiece.class, legoPiece.getId());
        if(setToFind == null) {
            throw new PersistenceException("Piece is not in database");
        }
        try{
            LegoPiece legoPieceToDelete = entityManager.merge(legoPiece);
            entityManager.remove(legoPieceToDelete);
        }catch(IllegalArgumentException ex){
            throw new PersistenceException(ex);
        }
    }

    @Override
    public void addLegoPiece(LegoPiece legoPiece) {
        String error = isValidLegoPiece(legoPiece);
        if (error.length() != 0) throw new IllegalArgumentException(error);
        entityManager.persist(legoPiece);
    }

    @Override
    public LegoPiece findLegoPieceById(Long id) {
        if(id == null){
            throw new IllegalArgumentException("given LegoPiece id is null");
        }
        return entityManager.find(LegoPiece.class, id);        
    }
    
    private String isValidLegoPiece(LegoPiece legoPiece) {
        String error;
        if(legoPiece == null){
            error = "lego piece is null";
        }else if(legoPiece.getColor() == null){
            error = "lego piece color is null";
        }else if(legoPiece.getLegoKits() == null){
            error = "lego piece kits set is null";
        }else{
            error = "";
        }
        return error;
    }
}
