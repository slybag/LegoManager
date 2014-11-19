/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.legomanager.web;

import cz.muni.fi.pa165.legomanager.services.LegoPieceService;
import cz.muni.fi.pa165.legomanager.transferobjects.LegoPieceTO;
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
 * @author Tomas
 */
@UrlBinding("/piece/{$event}/{piece.id}")
public class LegoPieceActionBean extends BaseActionBean implements ValidationErrorHandler {


    final static Logger log = LoggerFactory.getLogger(LegoSetActionBean.class);
    
    @SpringBean
    protected LegoPieceService legoPieceService;

    private List<LegoPieceTO> legoPieces;

    @DefaultHandler
    public Resolution list() {
        log.debug("list()");
        legoPieces = legoPieceService.getAllLegoPieces();
        log.debug("legoPieces.size=" + legoPieces.size());
        return new ForwardResolution("/piece/list.jsp");
    
    }
    
    public List<LegoPieceTO> getLegoPieces() {
        return legoPieces;
    }        
    
        
    @Override
    public Resolution handleValidationErrors(ValidationErrors ve) throws Exception {
        legoPieces = legoPieceService.getAllLegoPieces();
        return null;
    }
    
    @ValidateNestedProperties(value = {            
            @Validate(on = {"add", "save"}, field = "color", required = true),
    })
    private LegoPieceTO legoPieceTO;

    public LegoPieceTO getLegoPieceTO() {
        return legoPieceTO;
    }

    public void setLegoPieceTO(LegoPieceTO legoPieceTO) {
        this.legoPieceTO = legoPieceTO;
    }        
    
    public Resolution add() {
        log.debug("add() lego piece={}", legoPieceTO);
        legoPieceService.createLegoPiece(legoPieceTO);
        getContext().getMessages().add(new LocalizableMessage("piece.add.message", escapeHTML(legoPieceTO.getColor().toString())));
        return new RedirectResolution(this.getClass(), "list");
    }
    
    public Resolution delete() {
        log.debug("delete({})", legoPieceTO.getId());
        legoPieceTO = legoPieceService.getLegoPiece(legoPieceTO.getId());
        legoPieceService.removeLegoPiece(legoPieceTO);
        getContext().getMessages().add(new LocalizableMessage("piece.delete.message", escapeHTML(legoPieceTO.getColor().toString())));
        return new RedirectResolution(this.getClass(), "list");
    }
    
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
    public void loadLegoSetFromDatabase() {
        String ids = getContext().getRequest().getParameter("piece.id");
        if (ids == null) return;
        legoPieceTO = legoPieceService.getLegoPiece(Long.parseLong(ids));        
    }
    
    public Resolution edit() {
        log.debug("edit() piece={}", legoPieceTO);
        return new ForwardResolution("/piece/edit.jsp");
    }

    public Resolution save() {
        log.debug("save() piece={}", legoPieceTO);
        legoPieceService.updateLegoPiece(legoPieceTO);
        return new RedirectResolution(this.getClass(), "list");
    }
}
