/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.legomanager.web;

import cz.muni.fi.pa165.legomanager.services.LegoSetService;
import cz.muni.fi.pa165.legomanager.transferobjects.CategoryTO;
import cz.muni.fi.pa165.legomanager.transferobjects.LegoKitTO;
import cz.muni.fi.pa165.legomanager.transferobjects.LegoSetTO;
import static cz.muni.fi.pa165.legomanager.web.BaseActionBean.escapeHTML;
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
    protected LegoSetService legoSetService;

    private List<LegoSetTO> legoSets;

    @DefaultHandler
    public Resolution list() {
        log.debug("list()");        
        legoSets = legoSetService.getAllLegoSets();
        return new ForwardResolution("/set/list.jsp");
    
    }
    
    public List<LegoSetTO> getLegoSets() {
        return legoSets;
    }        
    
        
    @Override
    public Resolution handleValidationErrors(ValidationErrors ve) throws Exception {
        legoSets = legoSetService.getAllLegoSets();
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
    
    
    public Resolution add() {
        log.debug("add() lego set={}", legoSetTO);
        legoSetTO.setCategories(new HashSet<CategoryTO>());
        legoSetTO.setLegoKits(new ArrayList<LegoKitTO>());        
        legoSetService.createLegoSet(legoSetTO);
        getContext().getMessages().add(new LocalizableMessage("set.add.message", escapeHTML(legoSetTO.getName()), escapeHTML(legoSetTO.getPrice().toString())));
        return new RedirectResolution(this.getClass(), "list");
    }
    
    public Resolution delete() {
        log.debug("delete({})", legoSetTO.getId());
        LegoSetTO legoSetTOtoDelete = legoSetService.getLegoSet(legoSetTO.getId());
        legoSetService.removeLegoSet(legoSetTOtoDelete);
        getContext().getMessages().add(new LocalizableMessage("set.delete.message", escapeHTML(legoSetTO.getName())));
        return new RedirectResolution(this.getClass(), "list");
    }
    
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
    public void loadLegoSetFromDatabase() {
        String ids = getContext().getRequest().getParameter("set.id");
        if (ids == null) return;
        legoSetTO = legoSetService.getLegoSet(Long.parseLong(ids));        
    }
    
    public Resolution edit() {
        log.debug("edit() set={}", legoSetTO);
        return new ForwardResolution("/set/edit.jsp");
    }

    public Resolution save() {
        log.debug("save() set={}", legoSetTO);
        legoSetService.updateLegoSet(legoSetTO);
        return new RedirectResolution(this.getClass(), "list");
    }
}
