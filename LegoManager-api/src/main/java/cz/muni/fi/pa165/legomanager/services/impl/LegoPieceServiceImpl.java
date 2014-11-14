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
import cz.muni.fi.pa165.legomanager.dao.LegoPieceDao;
import cz.muni.fi.pa165.legomanager.entity.LegoPiece;
import java.util.ArrayList;
import org.dozer.DozerBeanMapper;

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
    public void createLegoPiece(LegoPieceTO legoPiece) {
        if(legoPiece==null) throw new IllegalArgumentException();
        legoPieceDao.addLegoPiece(mapper.map(legoPiece, LegoPiece.class));     
    }

    @Override
    @Transactional
    public void updateLegoPiece(LegoPieceTO legoPiece) {
        if(legoPiece==null) throw new IllegalArgumentException();
        legoPieceDao.updateLegoPiece(mapper.map(legoPiece, LegoPiece.class));    
    }

    @Override
    @Transactional
    public void removeLegoPiece(LegoPieceTO legoPiece) {
        if(legoPiece==null) throw new IllegalArgumentException();
        legoPieceDao.deleteLegoPiece(mapper.map(legoPiece, LegoPiece.class));
        
    }

    @Override
    @Transactional
    public LegoPieceTO getLegoPiece(Long id) {
        if(id==null) throw new IllegalArgumentException();
        return mapper.map(legoPieceDao.findLegoPieceById(id), LegoPieceTO.class);      
    }

    @Override
    @Transactional
    public List<LegoPieceTO> getAllLegoPieces() {
        List<LegoPieceTO> legoPieceTOs;
        List<LegoPiece> legoPieces = legoPieceDao.getAllLegoPieces();
        legoPieceTOs = new ArrayList<>();
        for (LegoPiece piece : legoPieces) {
            legoPieceTOs.add(mapper.map(piece, LegoPieceTO.class));
        }
        return legoPieceTOs;
    }
    
}
