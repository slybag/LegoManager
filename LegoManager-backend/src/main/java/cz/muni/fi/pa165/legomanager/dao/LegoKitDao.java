package cz.muni.fi.pa165.legomanager.dao;

import cz.muni.fi.pa165.legomanager.entity.LegoKit;
import java.util.List;

/**
 *
 * @author Petr
 */
public interface LegoKitDao {
    
     /**
     * Finds all lego kits
     * @return list of all lego kits
     */
    public List<LegoKit> getAllLegoKits();
    
    /**
     * Update lego kit
     * @param legoKit to be updated
     */
    public void updateLegoKit(LegoKit kit);
    
    /**
     * Removes lego kit
     * @param legoKit to be deleted
     */     
    public void deleteLegoKit (LegoKit kit);
    
    /**
     * Add new lego kit
     * @param legoKit to be added   
     */
    public void addLegoKit (LegoKit kit);
    
    /**
     * Find lego kit by id
     * @param id of wanted lego kit
     * @return lego set with specified id
     */
    public LegoKit findLegoKitById (Long id);
}
