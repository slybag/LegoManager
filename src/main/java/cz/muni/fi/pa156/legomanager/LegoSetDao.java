/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa156.legomanager;

import cz.muni.fi.pa165.legomanager.entity.Category;
import cz.muni.fi.pa165.legomanager.entity.LegoSet;
import java.util.List;

/**
 *
 * @author Michal Rais
 */
public interface LegoSetDao {
    /**
     * Finds all lego sets
     * @return list of all lego sets
     * @throws IllegalArgumentException
     * @throws LegoDaoException
     */
    public List<LegoSet> getAllLegoSets() throws IllegalArgumentException,LegoDaoException;
    
    /**
     * Update lego set
     * @param legoSet to be updated
     * @throws IllegalArgumentException
     * @throws LegoDaoException
     */
    public void updateLegoSet(LegoSet legoSet) throws IllegalArgumentException,LegoDaoException;
    
    /**
     * Remove lego set
     * @param legoSet to be deleted
     * @throws IllegalArgumentException
     * @throws LegoDaoException
     */
    public void deleteLegoSet(LegoSet legoSet) throws IllegalArgumentException,LegoDaoException;
    
    /**
     * Add new lego set
     * @param legoSet to be added   
     * @throws IllegalArgumentException
     * @throws LegoDaoException 
     */
    public void addLegoSet(LegoSet legoSet) throws IllegalArgumentException,LegoDaoException;
    
    /**
     * Find lego set by id
     * @param id of wanted lego set
     * @return lego set with specified id
     * @throws IllegalArgumentException
     * @throws LegoDaoException 
     */
    public LegoSet findLegoSetById (Long id) throws IllegalArgumentException,LegoDaoException;
}
