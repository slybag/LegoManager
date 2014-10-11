/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.legomanager;

import cz.muni.fi.pa165.legomanager.entity.LegoPiece;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
        return entityManager.createQuery("SELECT p FROM LegoPiece P", LegoPiece.class).getResultList();
    }

    @Override
    public void updateLegoPiece(LegoPiece legoPiece) throws IllegalArgumentException, LegoDaoException {
        String error = isValidLegoPiece(legoPiece);
        if (error.length() != 0) throw new IllegalArgumentException(error);
        try{
            entityManager.merge(legoPiece);
        }catch(IllegalArgumentException ex){
            throw new LegoDaoException(ex);
        }
    }

    @Override
    public void deleteLegoPiece(LegoPiece legoPiece) throws LegoDaoException {
        try{
            entityManager.remove(legoPiece);
        }catch(IllegalArgumentException ex){
            throw new LegoDaoException(ex);
        }
    }

    @Override
    public void addLegoPiece(LegoPiece legoPiece) throws IllegalArgumentException {
        String error = isValidLegoPiece(legoPiece);
        if (error.length() != 0) throw new IllegalArgumentException(error);
        entityManager.persist(legoPiece);
    }

    @Override
    public LegoPiece findLegoPieceById(Long id) throws IllegalArgumentException, LegoDaoException {
        if(id == null){
            throw new IllegalArgumentException("given LegoPiece id is null");
        }
        LegoPiece setToFind = entityManager.find(LegoPiece.class, id);
        if (setToFind == null) throw new LegoDaoException("Lego Piece with id: " + id + " is not in DB");
        
        return setToFind;
    }
    
    private String isValidLegoPiece(LegoPiece legoPiece) {
        String error;
        if(legoPiece == null){
            error = "lego piece is null";
        }else if(legoPiece.getId() == null){
            error = "lego piece id is null";
        }else if(legoPiece.getColor() == null){
            error = "lego piece color is null";
        }else if(legoPiece.getKits() == null){
            error = "lego piece kits set is null";
        }else{
            error = "";
        }
        return error;
    }
}
