/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.legomanager.web;

import cz.muni.fi.pa165.legomanager.services.CategoryService;
import cz.muni.fi.pa165.legomanager.services.LegoKitService;
import cz.muni.fi.pa165.legomanager.services.LegoPieceService;
import cz.muni.fi.pa165.legomanager.services.LegoSetService;
import cz.muni.fi.pa165.legomanager.transferobjects.CategoryTO;
import cz.muni.fi.pa165.legomanager.transferobjects.LegoKitTO;
import cz.muni.fi.pa165.legomanager.transferobjects.LegoPieceTO;
import cz.muni.fi.pa165.legomanager.transferobjects.LegoSetTO;
import static cz.muni.fi.pa165.legomanager.web.LegoSetActionBean.log;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.LocalizableMessage;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.ValidationErrorHandler;
import net.sourceforge.stripes.validation.ValidationErrors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Petr Konecny
 */
@UrlBinding("/kit/{$event}/{kit.id}/")
public class LegoKitActionBean extends BaseActionBean implements ValidationErrorHandler {

    
    final static Logger log = LoggerFactory.getLogger(LegoKitActionBean.class);

    @SpringBean
    protected LegoKitService legoKitService;
    
    @SpringBean
    protected LegoSetService legoSetService;
    
    @SpringBean
    protected LegoPieceService legoPieceService;
    
    @SpringBean
    protected CategoryService categoryService;
    
    private List<LegoSetTO> legoSets;
    
    private List<LegoKitTO> legoKits;
    
    private List<CategoryTO> categories;
    
    private List<LegoPieceTO> legoPieces;
    
    private List<Long> pieceIDs;
    
    private List<Long> categoryIDs;
    
    private List<Long> setIDs;
    
    @ValidateNestedProperties(value = {            
            @Validate(on = {"add", "save"}, field = "name", required = true),
            @Validate(on = {"add", "save"}, field = "price", required = true, minvalue = 1)
    })
    private LegoKitTO legoKitTO;
    
    @DefaultHandler
    public Resolution list() {
        log.debug("list()");
        legoSets = legoSetService.getAllLegoSets();
        categories = categoryService.getAllCategories();
        legoPieces = legoPieceService.getAllLegoPieces();
        legoKits = legoKitService.getAllLegoKits();
        return new ForwardResolution("/kit/list.jsp");
    }
    
    @Override
    public Resolution handleValidationErrors(ValidationErrors ve) {
        legoKits = legoKitService.getAllLegoKits();
        return null;
    }
       
    public Resolution add() {
        log.debug("add() lego kit={}", legoKitTO);
        legoKitService.createLegoKit(legoKitTO);
        getContext().getMessages().add(new LocalizableMessage("kit.add.message", escapeHTML(legoKitTO.getName())));
        return new RedirectResolution(this.getClass(), "list");
    }
    
    public Resolution delete() {
        log.debug("delete({})", legoKitTO.getId());
        legoKitTO = legoKitService.getLegoKit(legoKitTO.getId());
        legoKitService.deleteLegoKit(legoKitTO);
        getContext().getMessages().add(new LocalizableMessage("kit.delete.message", escapeHTML(legoKitTO.getName())));
        return new RedirectResolution(this.getClass(), "list");
    }
    
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
    public void loadLegoKitFromDatabase() {
        String ids = getContext().getRequest().getParameter("kit.id");
        if (ids == null) return;
        legoKitTO = legoKitService.getLegoKit(Long.parseLong(ids));        
    }
    
     public Resolution edit() {
        pieceIDs = new ArrayList<Long>();
        categoryIDs = new ArrayList<Long>();
        setIDs = new ArrayList<Long>();
        
        legoSets = legoSetService.getAllLegoSets();
        legoKits = legoKitService.getAllLegoKits();
        categories = categoryService.getAllCategories();
        legoPieces = legoPieceService.getAllLegoPieces();
        
        for(LegoPieceTO piece : legoKitTO.getLegoPieces()){
            pieceIDs.add(piece.getId());
        }
        for(CategoryTO category : legoKitTO.getCategories()){
            categoryIDs.add(category.getId());
        }
        for(LegoSetTO set : legoKitTO.getLegoSets()){
            setIDs.add(set.getId());
        }
        log.debug("edit() kit={}", legoKitTO);
        return new ForwardResolution("/kit/edit.jsp");
    }
    
    
    public Resolution save() {
        log.debug("save() kit={} pieceIDs={} categoryIDs={} setIDs{}", legoKitTO, pieceIDs,categoryIDs,setIDs);
        List<LegoPieceTO> pieceList = new ArrayList<LegoPieceTO>();
        List<LegoSetTO> setList = new ArrayList<LegoSetTO>();
        Set<CategoryTO> categoryList = new HashSet<CategoryTO>();
        
        if(pieceIDs != null){
            for(Long id : pieceIDs){
                pieceList.add(legoPieceService.getLegoPiece(id));
            }
        }
        
        if(categoryIDs != null){
            for(Long id: categoryIDs){
                categoryList.add(categoryService.getCategory(id));
            }
        }
        
        if(setIDs != null) {
            for(Long id: setIDs){
                setList.add(legoSetService.getLegoSet(id));
            }
        }
        
        legoKitTO.setLegoPieces(pieceList);
        legoKitTO.setCategories(categoryList);
        legoKitTO.setLegoSets(setList);
        legoKitService.updateLegoKit(legoKitTO);
        return new RedirectResolution(this.getClass(), "list");
    } 
     
    public List<LegoKitTO> getLegoKits() {
        return legoKits;
    }

    public LegoKitTO getLegoKitTO() {
        return legoKitTO;
    }

    public void setLegoKitTO(LegoKitTO legoKitTO) {
        this.legoKitTO = legoKitTO;
    }
   
    public void kitLegoKitTO(LegoKitTO legoKitTO) {
        this.legoKitTO = legoKitTO;
    }

    public List<LegoSetTO> getLegoSets() {
        return legoSets;
    }

    public List<CategoryTO> getCategories() {
        return categories;
    }

    public List<LegoPieceTO> getLegoPieces() {
        return legoPieces;
    }

    public List<Long> getPieceIDs() {
        return pieceIDs;
    }

    public void setPieceIDs(List<Long> pieceIDs) {
        this.pieceIDs = pieceIDs;
    }

    public List<Long> getCategoryIDs() {
        return categoryIDs;
    }

    public void setCategoryIDs(List<Long> categoryIDs) {
        this.categoryIDs = categoryIDs;
    }

    public List<Long> getSetIDs() {
        return setIDs;
    }

    public void setSetIDs(List<Long> setIDs) {
        this.setIDs = setIDs;
    }
    
    
    
    
    
    
    
     
}
