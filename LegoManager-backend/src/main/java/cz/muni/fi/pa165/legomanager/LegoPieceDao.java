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
 * @author Tomas
 */
public interface LegoPieceDao {
    /**
     * Gets all lego pieces stored in database in list
     * @return list of lego pieces
     */
    public List<LegoPiece> getAllLegoPieces();
    
    /**
     * Update lego piece based on its id
     * @param legoPiece new lego piece with old lego piece id
     * @throws IllegalArgumentException when lego piece is not valid
     * @throws LegoDaoException when lego piece don't exist
     */
    public void updateLegoPiece(LegoPiece legoPiece) throws IllegalArgumentException,LegoDaoException;
    
    /**
     * Delete lego piece based on its id
     * @param legoPiece lego piece to be deleted
     * @throws LegoDaoException when piece don't exist
     */
    public void deleteLegoPiece (LegoPiece legoPiece) throws LegoDaoException;
    
    /**
     * Add new lego piece to database
     * @param legoPiece lego piece to be added
     * @throws IllegalArgumentException when lego piece is not valid
     */
    public void addLegoPiece (LegoPiece legoPiece) throws IllegalArgumentException;
    
    /**
     * Find lego piece based on its id
     * @param id id of lego piece to be found
     * @return found lego piece by id
     * @throws IllegalArgumentException when id is null
     * @throws LegoDaoException when lego piece with id is not in database
     */
    public LegoPiece findLegoPieceById (Long id) throws IllegalArgumentException,LegoDaoException;
}
