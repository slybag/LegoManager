/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.legomanager.web;

import cz.muni.fi.pa165.legomanager.facades.LegoFacade;
import cz.muni.fi.pa165.legomanager.transferobjects.CategoryTO;
import cz.muni.fi.pa165.legomanager.transferobjects.LegoKitTO;
import cz.muni.fi.pa165.legomanager.transferobjects.LegoPieceTO;
import cz.muni.fi.pa165.legomanager.transferobjects.LegoSetTO;
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
    protected LegoFacade facade;
    private List<LegoKitTO> legoKits;
    private List<LegoPieceTO> legoPieces;
    private List<Long> pieceIDs;
    @ValidateNestedProperties(value = {
        @Validate(on = {"add", "save"}, field = "name", required = true),
        @Validate(on = {"add", "save"}, field = "price", required = true, minvalue = 1)
    })
    private LegoKitTO legoKitTO;

    @DefaultHandler
    public Resolution list() {
        log.debug("list()");
        legoPieces = facade.getAllLegoPieces();
        legoKits = facade.getAllLegoKits();
        return new ForwardResolution("/kit/list.jsp");
    }

    @Override
    public Resolution handleValidationErrors(ValidationErrors ve) {
        legoKits = facade.getAllLegoKits();
        return null;
    }

    public Resolution add() {
        log.debug("add() lego kit={}", legoKitTO);
        List<LegoPieceTO> pieceList = new ArrayList<LegoPieceTO>();

        if (pieceIDs != null) {
            for (Long id : pieceIDs) {
                pieceList.add(facade.getLegoPieceById(id));
            }
        }


        legoKitTO.setLegoPieces(pieceList);
        legoKitTO.setCategories(new HashSet<CategoryTO>());
        legoKitTO.setLegoSets(new ArrayList<LegoSetTO>());
        log.debug("save() before update category count={} sets count ={}", legoKitTO.getCategories().size(), legoKitTO.getLegoSets().size());
        facade.create(legoKitTO);
        getContext().getMessages().add(new LocalizableMessage("kit.add.message", escapeHTML(legoKitTO.getName())));
        return new RedirectResolution(this.getClass(), "list");
    }
    
    public Resolution details(){
        log.debug("details() kit={}", legoKitTO);
        return new ForwardResolution("/kit/details.jsp");
    }

    public Resolution delete() {
        log.debug("delete({})", legoKitTO.getId());
        legoKitTO = facade.getLegoKitById(legoKitTO.getId());
        facade.delete(legoKitTO);
        getContext().getMessages().add(new LocalizableMessage("kit.delete.message", escapeHTML(legoKitTO.getName())));
        return new RedirectResolution(this.getClass(), "list");
    }

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save", "details"})
    public void loadLegoKitFromDatabase() {
        String ids = getContext().getRequest().getParameter("kit.id");
        if (ids == null) {
            return;
        }
        legoKitTO = facade.getLegoKitById(Long.parseLong(ids));
        log.debug("load() kit={}", legoKitTO);
    }

    public Resolution edit() {
        pieceIDs = new ArrayList<Long>();

        legoKits = facade.getAllLegoKits();
        legoPieces = facade.getAllLegoPieces();

        for (LegoPieceTO piece : legoKitTO.getLegoPieces()) {
            pieceIDs.add(piece.getId());
        }
        log.debug("edit() kit={}", legoKitTO);
        return new ForwardResolution("/kit/edit.jsp");
    }

    public Resolution save() {
        log.debug("save() kit={} pieceIDs={} categoryIDs={} setIDs{}", legoKitTO, pieceIDs);
        List<LegoPieceTO> pieceList = new ArrayList<LegoPieceTO>();

        if (pieceIDs != null) {
            for (Long id : pieceIDs) {
                pieceList.add(facade.getLegoPieceById(id));
            }
        }

        legoKitTO.setLegoPieces(pieceList);
        log.debug("save() before update category count={} sets count ={}", legoKitTO.getCategories().size(), legoKitTO.getLegoSets().size());
        facade.update(legoKitTO);
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

    public List<LegoPieceTO> getLegoPieces() {
        return legoPieces;
    }

    public List<Long> getPieceIDs() {
        return pieceIDs;
    }

    public void setPieceIDs(List<Long> pieceIDs) {
        this.pieceIDs = pieceIDs;
    }
}
