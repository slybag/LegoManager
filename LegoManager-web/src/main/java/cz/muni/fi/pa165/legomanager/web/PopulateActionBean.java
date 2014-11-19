/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.legomanager.web;

import cz.muni.fi.pa165.legomanager.services.CategoryService;
import cz.muni.fi.pa165.legomanager.services.LegoKitService;
import cz.muni.fi.pa165.legomanager.services.LegoPieceService;
import cz.muni.fi.pa165.legomanager.services.LegoSetService;
import cz.muni.fi.pa165.legomanager.support.Color;
import cz.muni.fi.pa165.legomanager.transferobjects.CategoryTO;
import cz.muni.fi.pa165.legomanager.transferobjects.LegoKitTO;
import cz.muni.fi.pa165.legomanager.transferobjects.LegoPieceTO;
import cz.muni.fi.pa165.legomanager.transferobjects.LegoSetTO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.integration.spring.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Petr
 * 
 * Fills database with some test data
 */

public class PopulateActionBean extends BaseActionBean {
    
    final static Logger log = LoggerFactory.getLogger(PopulateActionBean.class);
    
    @SpringBean
    LegoKitService legoKitService;
    
    @SpringBean
    LegoSetService legoSetService;
    
    @SpringBean
    CategoryService categoryService;
    
    @SpringBean
    LegoPieceService legoPieceService;
    
    
    @DefaultHandler
    public Resolution populate() {
    
        LegoKitTO kit = new LegoKitTO();
        LegoSetTO set = new LegoSetTO();
        LegoPieceTO piece = new LegoPieceTO();
        CategoryTO category = new CategoryTO();
        
        for(int i = 0;i<10;i++){
            kit.setAgeRestriction(i+10);
            kit.setName("Star wars");
            kit.setPrice(BigDecimal.TEN);
            kit.setCategories(new HashSet<CategoryTO>());
            kit.setLegoPieces(new ArrayList<LegoPieceTO>());
            kit.setLegoSets(new ArrayList<LegoSetTO>());
            legoKitService.createLegoKit(kit);

            set.setCategories(new HashSet<CategoryTO>());
            set.setLegoKits(new ArrayList<LegoKitTO>());
            set.setName("Star trek" + i);
            set.setPrice(BigDecimal.TEN);
            legoSetService.createLegoSet(set);

            piece.setColor(Color.PieceColor.BLACK);
            piece.setLegoKits(new ArrayList<LegoKitTO>());
            legoPieceService.createLegoPiece(piece);

            category.setDescription("Some category");
            category.setName("Scifi"+i);
            category.setLegoKits(new ArrayList<LegoKitTO>());
            category.setLegoSets(new ArrayList<LegoSetTO>());
            categoryService.createCategory(category);
        }
        return new ForwardResolution("/index.jsp");
    }   
}
