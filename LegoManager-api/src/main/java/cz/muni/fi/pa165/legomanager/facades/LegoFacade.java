/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.legomanager.facades;

import cz.muni.fi.pa165.legomanager.transferobjects.CategoryTO;
import cz.muni.fi.pa165.legomanager.transferobjects.LegoKitTO;
import cz.muni.fi.pa165.legomanager.transferobjects.LegoPieceTO;
import cz.muni.fi.pa165.legomanager.transferobjects.LegoSetTO;
import java.util.List;

/**
 *
 * @author xkonecn1
 */
public interface LegoFacade {
    
    public void create(LegoKitTO kit);
            
    public void create(LegoSetTO set);

    public void create(LegoPieceTO piece);

    public void create(CategoryTO category);
    
    public void update(LegoKitTO kit);
            
    public void update(LegoSetTO set);

    public void update(LegoPieceTO piece);

    public void update(CategoryTO category);
    
    public LegoKitTO getLegoKitById (Long id);
            
    public LegoSetTO getLegoSetById (Long id);

    public LegoPieceTO getLegoPieceById(Long id);

    public CategoryTO getCategoryById(Long id);
    
    public List<LegoKitTO> getAllLegoKits();
            
    public List<LegoSetTO> getAllLegoSets();

    public List<LegoPieceTO> getAllLegoPieces();

    public List<CategoryTO> getAllCategories();
    
    public void delete(LegoKitTO kit);

    public void delete(LegoSetTO set);

    public void delete(LegoPieceTO piece);

    public void delete(CategoryTO category);
       
}
