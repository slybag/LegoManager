package cz.muni.fi.pa165.legomanager.dao;

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
     */
    public void updateLegoPiece(LegoPiece legoPiece);
    
    /**
     * Delete lego piece based on its id
     * @param legoPiece lego piece to be deleted
     */
    public void deleteLegoPiece (LegoPiece legoPiece);
    
    /**
     * Add new lego piece to database
     * @param legoPiece lego piece to be added
     */
    public void addLegoPiece (LegoPiece legoPiece);
    
    /**
     * Find lego piece based on its id
     * @param id id of lego piece to be found
     * @return found lego piece by id
     */
    public LegoPiece findLegoPieceById (Long id);
}
