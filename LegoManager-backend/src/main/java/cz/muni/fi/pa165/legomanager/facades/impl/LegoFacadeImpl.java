package cz.muni.fi.pa165.legomanager.facades.impl;

import cz.muni.fi.pa165.legomanager.facades.LegoFacade;
import cz.muni.fi.pa165.legomanager.services.CategoryService;
import cz.muni.fi.pa165.legomanager.services.LegoKitService;
import cz.muni.fi.pa165.legomanager.services.LegoPieceService;
import cz.muni.fi.pa165.legomanager.services.LegoSetService;
import cz.muni.fi.pa165.legomanager.services.UserService;
import cz.muni.fi.pa165.legomanager.transferobjects.CategoryTO;
import cz.muni.fi.pa165.legomanager.transferobjects.LegoKitTO;
import cz.muni.fi.pa165.legomanager.transferobjects.LegoPieceTO;
import cz.muni.fi.pa165.legomanager.transferobjects.LegoSetTO;
import cz.muni.fi.pa165.legomanager.transferobjects.UserTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

/**
 *
 * @author xkonecn1
 */
@Service
@Secured({"ROLE_USER","ROLE_ADMIN"})
public class LegoFacadeImpl implements LegoFacade {
    
    
    @Autowired
    LegoKitService legoKitService;
    
    @Autowired
    LegoSetService legoSetService;
    
    @Autowired
    LegoPieceService legoPieceService;
    
    @Autowired
    CategoryService categoryService;
    
    @Autowired
    UserService userService;
           
    @Override
    public void create(LegoKitTO kit){
        legoKitService.createLegoKit(kit);
    }
            
    @Override
    public void create(LegoSetTO set){
        legoSetService.createLegoSet(set);
    }

    @Override
    public void create(LegoPieceTO piece){
        legoPieceService.createLegoPiece(piece);
    }

    @Override
    public void create(CategoryTO category){
        categoryService.createCategory(category);
    }
    
    @Override
    public void create(UserTO user){
        userService.createUser(user);
    }
    
    @Override
    public void update(LegoKitTO kit){
        legoKitService.updateLegoKit(kit);
    }
            
    @Override
    public void update(LegoSetTO set){
        legoSetService.updateLegoSet(set);
    }

    @Override
    public void update(LegoPieceTO piece){
        legoPieceService.updateLegoPiece(piece);
    }

    @Override
    public void update(CategoryTO category){
        categoryService.updateCategory(category);
    }
    
    @Override
    public void update(UserTO user){
        userService.updateUser(user);
    }
    
    @Override
    public LegoKitTO getLegoKitById (Long id){
        return legoKitService.getLegoKit(id);
    }
            
    @Override
    public LegoSetTO getLegoSetById (Long id){
        return legoSetService.getLegoSet(id);
    }

    @Override
    public LegoPieceTO getLegoPieceById(Long id){
        return legoPieceService.getLegoPiece(id);
    }

    @Override
    public CategoryTO getCategoryById(Long id){
        return categoryService.getCategory(id);
    }
    
    @Override
    public UserTO getUserById(String id){
        return userService.getUser(id);
    }
    
    @Override
    public List<LegoKitTO> getAllLegoKits(){
        return legoKitService.getAllLegoKits();
    }
            
    @Override
    public List<LegoSetTO> getAllLegoSets(){
        return legoSetService.getAllLegoSets();
    }

    @Override
    public List<LegoPieceTO> getAllLegoPieces(){
        return legoPieceService.getAllLegoPieces();
    }

    @Override
    public List<CategoryTO> getAllCategories(){
        return categoryService.getAllCategories();
    }
    
    @Override
    public List<UserTO> getAllUsers(){
        return userService.getAllUsers();
    }
    
    @Override
    public void delete(LegoKitTO kit){
        legoKitService.deleteLegoKit(kit);
    }
            
    @Override
    public void delete(LegoSetTO set){
        legoSetService.removeLegoSet(set);
    }

    @Override
    public void delete(LegoPieceTO piece){
        legoPieceService.removeLegoPiece(piece);
    }

    @Override
    public void delete(CategoryTO category){
        categoryService.deleteCategory(category);
    }
    
    @Override
    public void delete(UserTO user){
        userService.deleteUser(user);
    }
    
}
