<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:layout-render name="/layout.jsp" titlekey="kit.details.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.legomanager.web.LegoKitActionBean" var="actionBean"/>

            <fieldset><legend><f:message key="kit.details"/></legend>
                <%@include file="show.jsp"%>
            </fieldset>

    </s:layout-component>
</s:layout-render>
