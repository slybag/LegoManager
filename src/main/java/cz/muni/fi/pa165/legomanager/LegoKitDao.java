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
    
    public List<LegoKit> getAllLegoKits() throws IllegalArgumentException,LegoDaoException;
    public void updateLegoKit(LegoKit kit) throws IllegalArgumentException,LegoDaoException;
    public void deleteLegoKit (LegoKit kit) throws IllegalArgumentException,LegoDaoException;
    public void addLegoKit (LegoKit kit) throws IllegalArgumentException,LegoDaoException;
    public LegoKit findLegoKitById (Long id) throws IllegalArgumentException,LegoDaoException;
}