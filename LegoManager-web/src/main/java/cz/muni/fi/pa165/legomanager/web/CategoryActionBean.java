/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.legomanager.web;

import cz.muni.fi.pa165.legomanager.facades.LegoFacade;
import cz.muni.fi.pa165.legomanager.services.CategoryService;
import cz.muni.fi.pa165.legomanager.transferobjects.CategoryTO;
import cz.muni.fi.pa165.legomanager.transferobjects.LegoKitTO;
import cz.muni.fi.pa165.legomanager.transferobjects.LegoPieceTO;
import cz.muni.fi.pa165.legomanager.transferobjects.LegoSetTO;
import static cz.muni.fi.pa165.legomanager.web.BaseActionBean.escapeHTML;
import static cz.muni.fi.pa165.legomanager.web.LegoKitActionBean.log;
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
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author xrais
 */
@UrlBinding("/category/{$event}/{category.id}/")
public class CategoryActionBean extends BaseActionBean implements ValidationErrorHandler {

    final static Logger log = LoggerFactory.getLogger(LegoPieceActionBean.class);
    @SpringBean
    private List<Long> kitIDs;
    private List<Long> setIDs;
    private List<LegoSetTO> legoSets;
    private List<LegoKitTO> legoKits;
    private List<CategoryTO> categories;

    @SpringBean
    protected LegoFacade facade;
    
    @DefaultHandler
    public Resolution list() {
        log.debug("list()");
        categories = facade.getAllCategories();
        legoSets = facade.getAllLegoSets();
        legoKits = facade.getAllLegoKits();
        log.debug("categories.size=" + categories.size());
        return new ForwardResolution("/category/list.jsp");

    }

    public List<CategoryTO> getCategories() {
        return categories;
    }

    @Override
    public Resolution handleValidationErrors(ValidationErrors ve) throws Exception {
        categories = facade.getAllCategories();
        legoKits = facade.getAllLegoKits();
        legoSets = facade.getAllLegoSets();
        return null;
    }
    @ValidateNestedProperties(value = {
        @Validate(on = {"add", "save"}, field = "name", required = true),
        @Validate(on = {"add", "save"}, field = "description", required = true)
    })
    private CategoryTO categoryTO;

    public CategoryTO getCategoryTO() {
        return categoryTO;
    }

    public void setCategoryTO(CategoryTO categoryTO) {
        this.categoryTO = categoryTO;
    }

    public Resolution add() {
        log.debug("add() category={}", categoryTO);
        List<LegoSetTO> setList = new ArrayList<LegoSetTO>();
        List<LegoKitTO> kitList = new ArrayList<LegoKitTO>();

        if (setIDs != null) {
            for (Long id : setIDs) {
                setList.add(facade.getLegoSetById(id));
            }
        }
        
        if (kitIDs != null) {
            for (Long id : kitIDs) {
                kitList.add(facade.getLegoKitById(id));
            }
        }
        categoryTO.setLegoKits(kitList);
        categoryTO.setLegoSets(setList);        
        facade.create(categoryTO);
        getContext().getMessages().add(new LocalizableMessage("category.add.message", escapeHTML(categoryTO.getName())));
        return new RedirectResolution(this.getClass(), "list");
    }

    public Resolution delete() {
        //log.debug("delete({})", categoryTO.getId());
        CategoryTO categoryTOdelete = facade.getCategoryById(categoryTO.getId());
        String name = new String(categoryTOdelete.getName());
        if(!categoryTOdelete.getLegoKits().isEmpty() || !categoryTOdelete.getLegoSets().isEmpty()){
            getContext().getMessages().add(new LocalizableMessage("delete.cant.category"));
            return new RedirectResolution(this.getClass(), "list");
        }
        facade.delete(categoryTOdelete);
        getContext().getMessages().add(new LocalizableMessage("category.delete.message", name));
        return new RedirectResolution(this.getClass(), "list");
    }
    
    public Resolution details(){
        log.debug("details() category={}", categoryTO);
        return new ForwardResolution("/category/details.jsp");
    }

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save", "details"})
    public void loadCategoryFromDatabase() {
        String ids = getContext().getRequest().getParameter("category.id");
        if (ids == null) {
            return;
        }
        categoryTO = facade.getCategoryById(Long.parseLong(ids));
    }

    public Resolution edit() {
        log.debug("edit() category={}", categoryTO);
        kitIDs = new ArrayList<Long>();
        setIDs = new ArrayList<Long>();

        legoKits = facade.getAllLegoKits();
        legoSets = facade.getAllLegoSets();

        for (LegoSetTO set : categoryTO.getLegoSets()) {
            setIDs.add(set.getId());
        }
        for (LegoKitTO kit : categoryTO.getLegoKits()) {
            kitIDs.add(kit.getId());
        }
        return new ForwardResolution("/category/edit.jsp");
        
    }

    public Resolution save() {
        log.debug("save() category={}", categoryTO);
        List<LegoSetTO> setList = new ArrayList<LegoSetTO>();
        List<LegoKitTO> kitList = new ArrayList<LegoKitTO>();

        if (kitIDs != null) {
            for (Long id : kitIDs) {
                kitList.add(facade.getLegoKitById(id));
            }
        }
        
        if (setIDs != null) {
            for (Long id : setIDs) {
                setList.add(facade.getLegoSetById(id));
            }
        }
        
        categoryTO.setLegoKits(kitList);
        categoryTO.setLegoSets(setList);
        facade.update(categoryTO);
        return new RedirectResolution(this.getClass(), "list");
    }

    public List<Long> getKitIDs() {
        return kitIDs;
    }

    public void setKitIDs(List<Long> kitIDs) {
        this.kitIDs = kitIDs;
    }

    public List<Long> getSetIDs() {
        return setIDs;
    }

    public void setSetIDs(List<Long> setIDs) {
        this.setIDs = setIDs;
    }

    public List<LegoSetTO> getLegoSets() {
        return legoSets;
    }

    public void setLegoSets(List<LegoSetTO> legoSets) {
        this.legoSets = legoSets;
    }

    public List<LegoKitTO> getLegoKits() {
        return legoKits;
    }

    public void setLegoKits(List<LegoKitTO> legoKits) {
        this.legoKits = legoKits;
    }
    
    
}
