/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.legomanager.services.impl;

import cz.muni.fi.pa165.legomanager.services.LegoPieceService;
import cz.muni.fi.pa165.legomanager.transferobjects.LegoPieceTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cz.muni.fi.pa165.legomanager.LegoPieceDao;
import cz.muni.fi.pa165.legomanager.entity.EntityDTOTransformer;
import cz.muni.fi.pa165.legomanager.entity.LegoPiece;
import java.util.ArrayList;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Tomas
 */
@Service
@Transactional
public class LegoPieceServiceImpl implements LegoPieceService{

    @Autowired
    LegoPieceDao legoPieceDao;

    public void setLegoPieceDAO(LegoPieceDao legoPieceDao) {
        this.legoPieceDao = legoPieceDao;
    }
    
    @Override
    @Transactional
    public void createLegoPiece(LegoPieceTO legoPiece) throws DataAccessException {
        LegoPiece legoPieceEntity = EntityDTOTransformer.legoPieceTOConvert(legoPiece);
        legoPieceDao.addLegoPiece(legoPieceEntity);
    }

    @Override
    @Transactional
    public void updateLegoPiece(LegoPieceTO legoPiece) throws DataAccessException {
        LegoPiece legoPieceEntity = EntityDTOTransformer.legoPieceTOConvert(legoPiece);
        legoPieceDao.updateLegoPiece(legoPieceEntity);
    }

    @Override
    @Transactional
    public void removeLegoPiece(LegoPieceTO legoPiece) throws DataAccessException {
        LegoPiece legoPieceEntity = EntityDTOTransformer.legoPieceTOConvert(legoPiece);
        legoPieceDao.deleteLegoPiece(legoPieceEntity);
    }

    @Override
    @Transactional
    public LegoPieceTO getLegoPiece(Long id) throws DataAccessException {
        return EntityDTOTransformer.legoPieceConvert(legoPieceDao.findLegoPieceById(id));
    }

    @Override
    @Transactional
    public List<LegoPieceTO> getAllLegoPieces() throws DataAccessException {
        List<LegoPieceTO> legoPiecesTO = new ArrayList<>();
        List<LegoPiece> legoPieces = legoPieceDao.getAllLegoPieces();
        for(LegoPiece lp : legoPieces){
            LegoPieceTO legoPieceTO = EntityDTOTransformer.legoPieceConvert(lp);
            legoPiecesTO.add(legoPieceTO);
        }
        return legoPiecesTO;
    }
    
}
