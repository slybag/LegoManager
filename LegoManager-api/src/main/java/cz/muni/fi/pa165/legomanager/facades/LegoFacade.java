package cz.muni.fi.pa165.legomanager.facades;

import cz.muni.fi.pa165.legomanager.transferobjects.CategoryTO;
import cz.muni.fi.pa165.legomanager.transferobjects.LegoKitTO;
import cz.muni.fi.pa165.legomanager.transferobjects.LegoPieceTO;
import cz.muni.fi.pa165.legomanager.transferobjects.LegoSetTO;
import cz.muni.fi.pa165.legomanager.transferobjects.UserTO;
import java.util.List;

/**
 *
 * @author xkonecn1
 */
public interface LegoFacade {
    
    /**
     * Creates new LegoKit
     * @param kit kit to be created in db
     */
    public void create(LegoKitTO kit);
    
    /**
     * Creates new LegoSet
     * @param set set to be created in db
     */
    public void create(LegoSetTO set);
    
    /**
     * Creates new LegoPiece
     * @param piece piece to be created in db
     */
    public void create(LegoPieceTO piece);

    /**
     * Creates new Category
     * @param category category to be created in db
     */
    public void create(CategoryTO category);
    
    /**
     * Creates new user
     * @param user user to be created in db
     */
    public void create(UserTO user);
    
    /**
     * Updates existing LegoKit
     * @param kit kit to be updated
     */
    public void update(LegoKitTO kit);

    /**
     * Updates existing LegoSet
     * @param set set to be updated
     */
    public void update(LegoSetTO set);

    /**
     * Updates existing LegoPiece
     * @param piece piece to be updated for
     */
    public void update(LegoPieceTO piece);

    /**
     * Updates existing Category
     * @param category category to be updated
     */
    public void update(CategoryTO category);
    
    /**
     * Updates existing user
     * @param user user to be updated
     */
    public void update(UserTO user);
    
    /**
     * Gets LegoKit from db by id
     * @param id id of kit to be selected from db
     * @return LegoKit with id equals to param id
     */
    public LegoKitTO getLegoKitById (Long id);
            
    /**
     * Gets LegoSet from db by id
     * @param id id of set to be selected from db
     * @return LegoSet with id equals to param id
     */
    public LegoSetTO getLegoSetById (Long id);

    /**
     * Gets LegoPiece from db by id
     * @param id id of piece to be selected from db
     * @return LegoPiece with id equals to param id
     */
    public LegoPieceTO getLegoPieceById(Long id);

    /**
     * Gets Category from db by id
     * @param id id of category to be selected from db
     * @return Category with id equals to param id
     */
    public CategoryTO getCategoryById(Long id);
    
    /**
     * Gets user from db by id
     * @param id id of user to be selected from db
     * @return user with id equals to param id
     */
    public UserTO getUserById(String id);
    
    /**
     * Gets all LegoKits
     * @return list of all lego kits
     */
    public List<LegoKitTO> getAllLegoKits();
            
    /**
     * Gets all LegoSets
     * @return list of all lego sets
     */
    public List<LegoSetTO> getAllLegoSets();

    /**
     * Gets all LegoPieces
     * @return list of all lego pieces
     */
    public List<LegoPieceTO> getAllLegoPieces();

    /**
     * Gets all Categories
     * @return list of all categories
     */
    public List<CategoryTO> getAllCategories();
    
    /**
     * Gets all users
     * @return list of all users
     */
    public List<UserTO> getAllUsers();
    
    /**
     * Delete LegoKit from db
     * @param kit kit to be deleted
     */
    public void delete(LegoKitTO kit);

    /**
     * Delete LegoSet from db
     * @param set set to be deleted
     */
    public void delete(LegoSetTO set);

    /**
     * Delete LegoPiece from db
     * @param piece piece to be deleted
     */
    public void delete(LegoPieceTO piece);

    /**
     * Delete Category from db
     * @param category category to be deleted
     */
    public void delete(CategoryTO category);
       
    /**
     * Delete user from db
     * @param user user to be deleted
     */
    public void delete(UserTO user);
}
