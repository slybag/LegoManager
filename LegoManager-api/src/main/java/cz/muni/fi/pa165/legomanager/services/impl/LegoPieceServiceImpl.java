/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.legomanager.services.impl;

import cz.muni.fi.pa165.legomanager.dao.LegoDaoException;
import cz.muni.fi.pa165.legomanager.services.LegoPieceService;
import cz.muni.fi.pa165.legomanager.transferobjects.LegoPieceTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cz.muni.fi.pa165.legomanager.dao.LegoPieceDao;
import cz.muni.fi.pa165.legomanager.entity.LegoPiece;
import java.util.ArrayList;
import org.dozer.DozerBeanMapper;
import org.dozer.MappingException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.RecoverableDataAccessException;

/**
 *
 * @author Tomas
 */
@Service
@Transactional
public class LegoPieceServiceImpl implements LegoPieceService{

    @Autowired
    LegoPieceDao legoPieceDao;
    
    @Autowired
    DozerBeanMapper mapper;

    public void setLegoPieceDao(LegoPieceDao legoPieceDao) {
        this.legoPieceDao = legoPieceDao;
    }

    public void setMapper(DozerBeanMapper mapper) {
        this.mapper = mapper;
    }
    
    
    
    @Override
    @Transactional
    public void createLegoPiece(LegoPieceTO legoPiece) throws DataAccessException {
        try{
            legoPieceDao.addLegoPiece(mapper.map(legoPiece, LegoPiece.class));
        }catch(IllegalArgumentException | LegoDaoException | MappingException ex){
            throw new RecoverableDataAccessException("Error while creating lego piece", ex);
        }
    }

    @Override
    @Transactional
    public void updateLegoPiece(LegoPieceTO legoPiece) throws DataAccessException {
        try{
            legoPieceDao.updateLegoPiece(mapper.map(legoPiece, LegoPiece.class));
        }catch(IllegalArgumentException | LegoDaoException | MappingException ex){
            throw new RecoverableDataAccessException("Error while updating lego piece", ex);
        }
    }

    @Override
    @Transactional
    public void removeLegoPiece(LegoPieceTO legoPiece) throws DataAccessException {
        try{
            legoPieceDao.deleteLegoPiece(mapper.map(legoPiece, LegoPiece.class));
        }catch(IllegalArgumentException | LegoDaoException | MappingException ex){
            throw new RecoverableDataAccessException("Error while deleting lego piece", ex);
        }
    }

    @Override
    @Transactional
    public LegoPieceTO getLegoPiece(Long id) throws DataAccessException {
        LegoPieceTO legoPiece;
        try{
            legoPiece = mapper.map(legoPieceDao.findLegoPieceById(id), LegoPieceTO.class);
        }catch(IllegalArgumentException | LegoDaoException | MappingException ex){
            throw new RecoverableDataAccessException("Error while retrieving lego piece", ex);
        }
        return legoPiece;
    }

    @Override
    @Transactional
    public List<LegoPieceTO> getAllLegoPieces() throws DataAccessException {
        List<LegoPieceTO> legoPieceTOs;
        try {
            List<LegoPiece> legoPieces = legoPieceDao.getAllLegoPieces();
            legoPieceTOs = new ArrayList<>();
            for (LegoPiece piece : legoPieces) {
                legoPieceTOs.add(mapper.map(piece, LegoPieceTO.class));
            }
        } catch (IllegalArgumentException | LegoDaoException | MappingException ex) {
            throw new RecoverableDataAccessException("Error while retrieving data", ex);
        }
        return legoPieceTOs;
    }
    
}
