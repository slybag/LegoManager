<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<s:layout-render name="/layout.jsp" titlekey="index.title">
    <s:layout-component name="body">

       <ul>
           <li><s:link beanclass="cz.muni.fi.pa165.legomanager.web.LegoSetActionBean"><f:message key="index.sets.link"/></s:link></li>
           <li><s:link beanclass="cz.muni.fi.pa165.legomanager.web.LegoKitActionBean"><f:message key="index.kits.link"/></s:link></li>
           <li><s:link beanclass="cz.muni.fi.pa165.legomanager.web.PopulateActionBean">Populate tables for testing</s:link></li>

           <li><s:link beanclass="cz.muni.fi.pa165.legomanager.web.LegoPieceActionBean"><f:message key="index.pieces.link"/></s:link></li>
           <li><s:link beanclass="cz.muni.fi.pa165.legomanager.web.CategoryActionBean"><f:message key="index.categories.link"/></s:link></li>
       </ul>

    </s:layout-component>
</s:layout-render>
