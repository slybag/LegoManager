<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:layout-render name="/layout.jsp" titlekey="kit.edit.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.legomanager.web.LegoKitActionBean" var="actionBean"/>

        <s:form beanclass="cz.muni.fi.pa165.legomanager.web.LegoKitActionBean">
            <s:hidden name="kit.id"/>
            <fieldset><legend><f:message key="kit.edit.edit"/></legend>
                <s:submit class="button bigtext" name="save"><f:message key="kit.edit.save"/></s:submit>
                <br>
                <br>
                <%@include file="form.jsp"%>
                <br>
                <s:submit class="button bigtext" name="save"><f:message key="kit.edit.save"/></s:submit>
                </fieldset>
        </s:form>

    </s:layout-component>
</s:layout-render>
