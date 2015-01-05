package cz.muni.fi.pa165.legomanager.web;

import cz.muni.fi.pa165.legomanager.facades.LegoFacade;
import cz.muni.fi.pa165.legomanager.transferobjects.UserTO;
import static cz.muni.fi.pa165.legomanager.web.BaseActionBean.escapeHTML;
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
@UrlBinding("/user/{$event}/{user.accountName}/")
public class UserActionBean extends BaseActionBean implements ValidationErrorHandler {

    final static Logger log = LoggerFactory.getLogger(UserActionBean.class);
        
    @SpringBean
    protected LegoFacade facade;

    private List<UserTO> users;    
    
    @DefaultHandler
    public Resolution list() {
        log.debug("list()");        
        users = facade.getAllUsers();
        return new ForwardResolution("/user/list.jsp");
    
    }
    
    public List<UserTO> getUsers() {
        return users;
    }        
    
        
    @Override
    public Resolution handleValidationErrors(ValidationErrors ve) throws Exception {
        users = facade.getAllUsers();
        return null;
    }
    
    @ValidateNestedProperties(value = {            
            @Validate(on = {"add"}, field = "accountName", required = true),
            @Validate(on = {"add", "save"}, field = "password", required = true),
            @Validate(on = {"add", "save"}, field = "role", required = true),
    })
    private UserTO userTO;

    public UserTO getUserTO() {
        return userTO;
    }

    public void setUserTO(UserTO userTO) {
        this.userTO = userTO;
    }    
       
    public Resolution details(){
        log.debug("details() user={}", userTO);
        return new ForwardResolution("/user/details.jsp");
    }
    
    
    public Resolution add() {
        log.debug("add() user={}", userTO);       
        facade.create(userTO);
        getContext().getMessages().add(new LocalizableMessage("user.add.message", escapeHTML(userTO.getAccountName()), escapeHTML(userTO.getPassword()), escapeHTML(userTO.getRole())));
        return new RedirectResolution(this.getClass(), "list");
    }
    
    public Resolution delete() {
        log.debug("delete({})", userTO.getAccountName());
        UserTO userTOtoDelete = facade.getUserById(userTO.getAccountName());
        String name = userTOtoDelete.getAccountName();
        facade.delete(userTOtoDelete);
        getContext().getMessages().add(new LocalizableMessage("set.delete.message", name));
        return new RedirectResolution(this.getClass(), "list");
    }
    
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save" , "details"})
    public void loadUserFromDatabase() {
        String ids = getContext().getRequest().getParameter("user.accountName");
        if (ids == null) return;
        userTO = facade.getUserById(ids);        
    }
    
    public Resolution edit() {
        log.debug("edit() user={}", userTO);
        return new ForwardResolution("/user/edit.jsp");
    }

    public Resolution save() {
        log.debug("save() user={}", userTO);
        facade.update(userTO);
        return new RedirectResolution(this.getClass(), "list");
    }
   
    
}
