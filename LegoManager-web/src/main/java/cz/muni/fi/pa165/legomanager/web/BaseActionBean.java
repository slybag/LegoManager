package cz.muni.fi.pa165.legomanager.web;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import org.apache.taglibs.standard.functions.Functions;


/**
 *
 * @author xrais
 */
public abstract class BaseActionBean implements ActionBean {
    private ActionBeanContext context;

    @Override
    public ActionBeanContext getContext() {
        return context;
    }

    @Override
    public void setContext(ActionBeanContext context) {
        this.context = context;
    }        
    
    public static String escapeHTML(String s) {
        return Functions.escapeXml(s);        
    }
}
