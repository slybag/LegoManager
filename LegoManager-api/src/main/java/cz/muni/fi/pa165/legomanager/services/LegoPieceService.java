package cz.muni.fi.pa165.legomanager.services;

import cz.muni.fi.pa165.legomanager.transferobjects.LegoPieceTO;
import java.util.List;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Tomas
 */
public interface LegoPieceService {
/**
* Creates lego piece
*
* @param legoPiece piece to be created
*/
public void createLegoPiece(LegoPieceTO legoPiece) throws DataAccessException;
/**
* Updates legoPiece
*
* @param legoPiece legoPiece to be updated
*/
public void updateLegoPiece(LegoPieceTO legoPiece) throws DataAccessException;
/**
* Removes legoPiece
*
* @param legoPiece legoPiece to be removed
*/
public void removeLegoPiece(LegoPieceTO legoPiece) throws DataAccessException;
/**
* Returns legoPiece according to ID
*
* @param id id of desired legoPiece
* @return legoPiece from DB
*/
public LegoPieceTO getLegoPiece(Long id) throws DataAccessException;
/**
* Returns all legoPieces from DB
*
* @return all legoPieces from DB
*/
public List<LegoPieceTO> getAllLegoPieces() throws DataAccessException;

}