package cz.muni.fi.pa165.legomanager.dao;

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
     */
    public List<LegoSet> getAllLegoSets();
    
    /**
     * Update lego set
     * @param legoSet to be updated
     */
    public void updateLegoSet(LegoSet legoSet);
    
    /**
     * Remove lego set
     * @param legoSet to be deleted
     * @throws IllegalArgumentException
     */
    public void deleteLegoSet(LegoSet legoSet);
    
    /**
     * Add new lego set
     * @param legoSet to be added   
     */
    public void addLegoSet(LegoSet legoSet);
    
    /**
     * Find lego set by id
     * @param id of wanted lego set
     * @return lego set with specified id
     */
    public LegoSet findLegoSetById (Long id);
}
