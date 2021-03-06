<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:layout-render name="/layout.jsp" titlekey="set.edit.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.legomanager.web.LegoSetActionBean" var="actionBean"/>
        <s:form beanclass="cz.muni.fi.pa165.legomanager.web.LegoSetActionBean">
            <s:hidden name="set.id"/>
            <fieldset><legend><f:message key="set.edit.edit"/></legend>
                <s:submit class="button bigtext" name="save"><f:message key="set.edit.save"/></s:submit>
                <br>
                <br>
                <%@include file="form.jsp"%>
                <br>
                <s:submit class="button bigtext" name="save"><f:message key="set.edit.save"/></s:submit>
                </fieldset>
        </s:form>

    </s:layout-component>
</s:layout-render>
