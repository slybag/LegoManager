/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.legomanager.web;

import cz.muni.fi.pa165.legomanager.services.CategoryService;
import cz.muni.fi.pa165.legomanager.transferobjects.CategoryTO;
import cz.muni.fi.pa165.legomanager.transferobjects.LegoKitTO;
import cz.muni.fi.pa165.legomanager.transferobjects.LegoSetTO;
import static cz.muni.fi.pa165.legomanager.web.BaseActionBean.escapeHTML;
import java.util.ArrayList;
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
@UrlBinding("/category/{$event}/{category.id}")
public class CategoryActionBean extends BaseActionBean implements ValidationErrorHandler {

    final static Logger log = LoggerFactory.getLogger(LegoPieceActionBean.class);
    
    @SpringBean
    protected CategoryService categoryService;

    private List<CategoryTO> categories;

    @DefaultHandler
    public Resolution list() {
        log.debug("list()");
        categories = categoryService.getAllCategories();
        log.debug("categories.size=" + categories.size());
        return new ForwardResolution("/category/list.jsp");
    
    }
    
    public List<CategoryTO> getCategories() {
        return categories;
    }        
    
        
    @Override
    public Resolution handleValidationErrors(ValidationErrors ve) throws Exception {
        categories = categoryService.getAllCategories();
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

    public void setSategoryTO(CategoryTO categoryTO) {
        this.categoryTO = categoryTO;
    }        
    
    public Resolution add() {
        log.debug("add() category={}", categoryTO);
        categoryTO.setLegoKits(new ArrayList<LegoKitTO>());
        categoryTO.setLegoSets(new ArrayList<LegoSetTO>());
        categoryService.createCategory(categoryTO);
        getContext().getMessages().add(new LocalizableMessage("category.add.message", escapeHTML(categoryTO.getName())));
        return new RedirectResolution(this.getClass(), "list");
    }
    
    public Resolution delete() {
        log.debug("delete({})", categoryTO.getId());
        categoryTO = categoryService.getCategory(categoryTO.getId());
        categoryService.deleteCategory(categoryTO);
        getContext().getMessages().add(new LocalizableMessage("category.delete.message", escapeHTML(categoryTO.getName())));
        return new RedirectResolution(this.getClass(), "list");
    }
    
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
    public void loadLegoSetFromDatabase() {
        String ids = getContext().getRequest().getParameter("category.id");
        if (ids == null) return;
        categoryTO = categoryService.getCategory(Long.parseLong(ids));        
    }
    
    public Resolution edit() {
        log.debug("edit() category={}", categoryTO);
        return new ForwardResolution("/category/edit.jsp");
    }

    public Resolution save() {
        log.debug("save() category={}", categoryTO);
        categoryService.updateCategory(categoryTO);
        return new RedirectResolution(this.getClass(), "list");
    }
    
}
