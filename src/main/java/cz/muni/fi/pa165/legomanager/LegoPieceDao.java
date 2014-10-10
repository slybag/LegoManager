/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pa165.legomanager;

import cz.muni.fi.pa165.legomanager.entity.LegoPiece;
import java.util.List;

/**
 *
 * @author Dazle
 */
public interface LegoPieceDao {
    public List<LegoPiece> getAllLegoPieces() throws IllegalArgumentException,LegoDaoException;
    public void updateLegoPiece(LegoPiece legoPiece) throws IllegalArgumentException,LegoDaoException;
    public void deleteLegoPiece (LegoPiece legoPiece) throws IllegalArgumentException,LegoDaoException;
    public void addLegoPiece (LegoPiece legoPiece) throws IllegalArgumentException,LegoDaoException;
    public LegoPiece findLegoPieceById (Long id) throws IllegalArgumentException,LegoDaoException;
}
