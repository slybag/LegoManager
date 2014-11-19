/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.legomanager.web;

import cz.muni.fi.pa165.legomanager.services.LegoKitService;
import cz.muni.fi.pa165.legomanager.transferobjects.CategoryTO;
import cz.muni.fi.pa165.legomanager.transferobjects.LegoKitTO;
import cz.muni.fi.pa165.legomanager.transferobjects.LegoPieceTO;
import cz.muni.fi.pa165.legomanager.transferobjects.LegoSetTO;
import static cz.muni.fi.pa165.legomanager.web.LegoSetActionBean.log;
import java.math.BigDecimal;
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
 * @author Petr Konecny
 */
@UrlBinding("/kit/{$event}/{kit.id}")
public class LegoKitActionBean extends BaseActionBean implements ValidationErrorHandler {

    
    final static Logger log = LoggerFactory.getLogger(LegoKitActionBean.class);

    @SpringBean
    protected LegoKitService legoKitService;
    
    private List<LegoKitTO> legoKits;
    
    @ValidateNestedProperties(value = {            
            @Validate(on = {"add", "save"}, field = "name", required = true),
            @Validate(on = {"add", "save"}, field = "price", required = true, minvalue = 1)
    })
    private LegoKitTO legoKitTO;
    
    @DefaultHandler
    public Resolution list() {
        log.debug("list()");
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
        log.debug("edit() kit={}", legoKitTO);
        return new ForwardResolution("/kit/edit.jsp");
    }
    
    public Resolution save() {
        log.debug("save() kit={}", legoKitTO);
        legoKitService.updateLegoKit(legoKitTO);
        return new RedirectResolution(this.getClass(), "list");
    } 
     
    public List<LegoKitTO> getLegoKits() {
        return legoKits;
    }

    public LegoKitTO getLegoKitTO() {
        return legoKitTO;
    }

    public void kitLegoKitTO(LegoKitTO legoKitTO) {
        this.legoKitTO = legoKitTO;
    }
     
}
