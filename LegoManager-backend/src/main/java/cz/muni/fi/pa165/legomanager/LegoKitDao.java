/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.legomanager;

import cz.muni.fi.pa165.legomanager.entity.Category;
import cz.muni.fi.pa165.legomanager.entity.LegoKit;
import cz.muni.fi.pa165.legomanager.entity.LegoPiece;
import cz.muni.fi.pa165.legomanager.entity.LegoSet;
import java.util.List;

/**
 *
 * @author Petr
 */
public interface LegoKitDao {
    
     /**
     * Finds all lego kits
     * @return list of all lego kits
     * @throws IllegalArgumentException
     * @throws LegoDaoException
     */
    public List<LegoKit> getAllLegoKits() throws IllegalArgumentException,LegoDaoException;
    
    /**
     * Update lego kit
     * @param legoKit to be updated
     * @throws IllegalArgumentException
     * @throws LegoDaoException
     */
    public void updateLegoKit(LegoKit kit) throws IllegalArgumentException,LegoDaoException;
    
    /**
     * Removes lego kit
     * @param legoKit to be deleted
     * @throws IllegalArgumentException
     * @throws LegoDaoException
     */     
    public void deleteLegoKit (LegoKit kit) throws IllegalArgumentException,LegoDaoException;
    
    /**
     * Add new lego kit
     * @param legoKit to be added   
     * @throws IllegalArgumentException
     * @throws LegoDaoException 
     */
    public void addLegoKit (LegoKit kit) throws IllegalArgumentException,LegoDaoException;
    
    /**
     * Find lego kit by id
     * @param id of wanted lego kit
     * @return lego set with specified id
     * @throws IllegalArgumentException
     * @throws LegoDaoException 
     */
    public LegoKit findLegoKitById (Long id) throws IllegalArgumentException,LegoDaoException;
}
