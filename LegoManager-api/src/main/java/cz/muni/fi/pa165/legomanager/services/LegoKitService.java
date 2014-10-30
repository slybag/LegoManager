/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.legomanager.services;

import cz.muni.fi.pa165.legomanager.transferobjects.LegoKitTO;
import java.util.List;
/**
 *
 * @author Petr Konecny
 */
public interface LegoKitService {
    
     /**
     * Finds all lego kits
     * @return list of all lego kits
     */
    public List<LegoKitTO> getAllLegoKits() ;
    
    /**
     * Update lego kit
     * @param legoKit
     */
    public void updateLegoKit(LegoKitTO legoKit);
    
    /**
     * Removes lego kit
     * @param legoKit to be deleted
     * @throws IllegalArgumentException
     */     
    public void deleteLegoKit (LegoKitTO legoKit);
    
    /**
     * Add new lego kit
     * @param legoKit to be added   
     */
    public void createLegoKit (LegoKitTO legoKit);
    
    /**
     * Find lego kit by id
     * @param id of wanted lego kit
     * @return lego set with specified id
     */
    public LegoKitTO getLegoKit (Long id);
}
