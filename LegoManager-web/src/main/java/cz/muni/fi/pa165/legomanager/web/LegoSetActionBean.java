/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.legomanager.web;

import cz.muni.fi.pa165.legomanager.facades.LegoFacade;
import cz.muni.fi.pa165.legomanager.transferobjects.CategoryTO;
import cz.muni.fi.pa165.legomanager.transferobjects.LegoKitTO;
import cz.muni.fi.pa165.legomanager.transferobjects.LegoSetTO;
import static cz.muni.fi.pa165.legomanager.web.BaseActionBean.escapeHTML;
import static cz.muni.fi.pa165.legomanager.web.LegoKitActionBean.log;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
 * @author xrais
 */
@UrlBinding("/set/{$event}/{set.id}/")
public class LegoSetActionBean extends BaseActionBean implements ValidationErrorHandler {

    final static Logger log = LoggerFactory.getLogger(LegoSetActionBean.class);
        
    @SpringBean
    protected LegoFacade facade;

    private List<LegoSetTO> legoSets;
    
    private List<Long> kitIDs;

    private List<LegoKitTO> legoKits;
    
    @DefaultHandler
    public Resolution list() {
        log.debug("list()");        
        legoSets = facade.getAllLegoSets();
        legoKits = facade.getAllLegoKits();
        return new ForwardResolution("/set/list.jsp");
    
    }
    
    public List<LegoSetTO> getLegoSets() {
        return legoSets;
    }        
    
        
    @Override
    public Resolution handleValidationErrors(ValidationErrors ve) throws Exception {
        legoSets = facade.getAllLegoSets();
        legoKits = facade.getAllLegoKits();
        return null;
    }
    
    @ValidateNestedProperties(value = {            
            @Validate(on = {"add", "save"}, field = "name", required = true),
            @Validate(on = {"add", "save"}, field = "price", required = true, minvalue = 1)
    })
    private LegoSetTO legoSetTO;
    

    public LegoSetTO getLegoSetTO() {
        return legoSetTO;
    }

    public void setLegoSetTO(LegoSetTO legoSetTO) {
        this.legoSetTO = legoSetTO;
    }
    
    public Resolution details(){
        log.debug("details() kit={}", legoSetTO);
        return new ForwardResolution("/set/details.jsp");
    }
    
    
    public Resolution add() {
        log.debug("add() lego set={}", legoSetTO);
        legoSetTO.setCategories(new HashSet<CategoryTO>());
        legoSetTO.setLegoKits(new ArrayList<LegoKitTO>());        
        facade.create(legoSetTO);
        getContext().getMessages().add(new LocalizableMessage("set.add.message", escapeHTML(legoSetTO.getName()), escapeHTML(legoSetTO.getPrice().toString())));
        return new RedirectResolution(this.getClass(), "list");
    }
    
    public Resolution delete() {
        log.debug("delete({})", legoSetTO.getId());
        LegoSetTO legoSetTOtoDelete = facade.getLegoSetById(legoSetTO.getId());
        String name = legoSetTOtoDelete.getName();
        if(!legoSetTOtoDelete.getLegoKits().isEmpty() || !legoSetTOtoDelete.getCategories().isEmpty()){
            getContext().getMessages().add(new LocalizableMessage("delete.cant.set"));
            return new RedirectResolution(this.getClass(), "list");
        }
        facade.delete(legoSetTOtoDelete);
        getContext().getMessages().add(new LocalizableMessage("set.delete.message", name));
        return new RedirectResolution(this.getClass(), "list");
    }
    
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save" , "details"})
    public void loadLegoSetFromDatabase() {
        String ids = getContext().getRequest().getParameter("set.id");
        if (ids == null) return;
        legoSetTO = facade.getLegoSetById(Long.parseLong(ids));        
    }
    
    public Resolution edit() {
        kitIDs = new ArrayList<Long>();

        legoKits = facade.getAllLegoKits();
        for (LegoKitTO kit : legoSetTO.getLegoKits()) {
            kitIDs.add(kit.getId());
        }
        log.debug("edit() kit={}", legoSetTO);
        return new ForwardResolution("/set/edit.jsp");
    }

    public Resolution save() {
        log.debug("save() set={}", legoSetTO);
        List<LegoKitTO> kitList = new ArrayList<LegoKitTO>();

        if (kitIDs != null) {
            for (Long id : kitIDs) {
                kitList.add(facade.getLegoKitById(id));
            }
        }
        legoSetTO.setLegoKits(kitList);
        facade.update(legoSetTO);
        return new RedirectResolution(this.getClass(), "list");
    }

    public List<Long> getKitIDs() {
        return kitIDs;
    }

    public void setKitIDs(List<Long> kitIDs) {
        this.kitIDs = kitIDs;
    }

    public List<LegoKitTO> getLegoKits() {
        return legoKits;
    }

    public void setLegoKits(List<LegoKitTO> legoKits) {
        this.legoKits = legoKits;
    }
    
    
}
