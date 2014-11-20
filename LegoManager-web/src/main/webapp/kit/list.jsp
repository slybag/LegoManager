<%@ page contentType="text/html; charkit=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="kit.list.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.legomanager.web.LegoKitActionBean" var="actionBean"/>

        <p><f:message key="kit.list.allkits"/></p>

        <table class="basic">
            <tr>
                <th>id</th>
                <th><f:message key="kit.name"/></th>
                <th><f:message key="kit.price"/></th>                
                <th></th>
                <th></th>
            </tr>
            <c:forEach items="${actionBean.legoKits}" var="kit">
                <tr>
                    <td>${kit.id}</td>
                    <td><c:out value="${kit.name}"/></td>
                    <td><c:out value="${kit.price}"/></td>                    
                    <td>
                     <s:link beanclass="cz.muni.fi.pa165.legomanager.web.LegoKitActionBean" event="edit"><s:param name="kit.id" value="${kit.id}"/>edit</s:link>
                    </td>
                    <td>
                        <s:form beanclass="cz.muni.fi.pa165.legomanager.web.LegoKitActionBean">
                            <s:hidden name="legoKitTO.id" value="${kit.id}"/>
                            <s:submit name="delete"><f:message key="kit.list.delete"/></s:submit>
                        </s:form>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <s:form beanclass="cz.muni.fi.pa165.legomanager.web.LegoKitActionBean">
            <fieldset><legend><f:message key="kit.list.newpiece"/></legend>
                <%@include file="form.jsp"%>
                <s:submit name="add"><f:message key="kit.create.newpiece"/></s:submit>
                </fieldset>
        </s:form>
    </s:layout-component>
</s:layout-render>