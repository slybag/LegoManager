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

/**
 *
 * @author Tomas
 */
public class LegoPieceDaoImpl implements LegoPieceDao {
    @PersistenceContext
    EntityManager entityManager;
    
    @Override
    public List<LegoPiece> getAllLegoPieces(){
        return entityManager.createQuery("SELECT p FROM LegoPiece P", LegoPiece.class).getResultList();
    }

    @Override
    public void updateLegoPiece(LegoPiece legoPiece) throws IllegalArgumentException, LegoDaoException {
        if (!isValidLegoPiece(legoPiece)) throw new IllegalArgumentException();
        try{
            entityManager.merge(legoPiece);
        }catch(IllegalArgumentException ex){
            throw new LegoDaoException(ex);
        }
    }

    @Override
    public void deleteLegoPiece(LegoPiece legoPiece) {
        try{
            entityManager.remove(legoPiece);
        }catch(IllegalArgumentException ex){
            throw new LegoDaoException(ex);
        }
    }

    @Override
    public void addLegoPiece(LegoPiece legoPiece) throws IllegalArgumentException {
        if (!isValidLegoPiece(legoPiece)) throw new IllegalArgumentException();
        entityManager.persist(legoPiece);
    }

    @Override
    public LegoPiece findLegoPieceById(Long id) throws IllegalArgumentException, LegoDaoException {
        if(id < 0){
            throw new IllegalArgumentException();
        }
        LegoPiece setToFind = entityManager.find(LegoPiece.class, id);
        if (setToFind == null) throw new LegoDaoException("Lego Piece with id: " + id + " is not in DB");
        
        return setToFind;
    }
    
    //prepared function
    private boolean isValidLegoPiece(LegoPiece legoPiece) {                        
        if (legoPiece == null || legoPiece.getKits() == null) return false;        
        return true;
    }
}
