<%@ page contentType="text/html; charkit=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:layout-render name="/layout.jsp" titlekey="kit.list.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.legomanager.web.LegoKitActionBean" var="actionBean"/>
        <c:set var="numberOfKits" scope="session" value="${actionBean.legoKits}"/>
        <div class="menu_container">
        <button class="button dialog_button bigtext" type='button' onclick='create();'><f:message key="kit.create"/></button>
        </div>
        <br>
        <div class="table_container">
            <table class="basic">
                <caption><f:message key="kit.list.allkits"/></caption>
                <c:choose>
                    <c:when test="${empty numberOfKits}">
                        <tr><td><f:message key="details.noKits"/></td></tr>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <th>id</th>
                            <th><f:message key="kit.name"/></th>
                            <th><f:message key="kit.price"/></th>                
                            <th></th>
                            <th></th>
                            <th></th>
                        </tr>
                        <c:forEach items="${actionBean.legoKits}" var="kit">
                            <tr>
                                <td>${kit.id}</td>
                                <td><c:out value="${kit.name}"/></td>
                                <td><c:out value="${kit.price}"/></td>                    
                                <td>
                                    <s:link class="button" beanclass="cz.muni.fi.pa165.legomanager.web.LegoKitActionBean" event="edit"><s:param name="kit.id" value="${kit.id}"/><f:message key="kit.edit"/></s:link>
                                    </td>
                                    <td>
                                    <s:link class="button" beanclass="cz.muni.fi.pa165.legomanager.web.LegoKitActionBean" event="details"><s:param name="kit.id" value="${kit.id}"/><f:message key="kit.details"/></s:link>
                                    </td>
                                    <td>
                                    <s:form beanclass="cz.muni.fi.pa165.legomanager.web.LegoKitActionBean">
                                        <s:hidden name="legoKitTO.id" value="${kit.id}"/>
                                        <s:submit class="button" name="delete"><f:message key="kit.list.delete"/></s:submit>
                                    </s:form>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </table>
        </div>
        <div class="hidden" id="add" title="Add the kit">
            <s:form beanclass="cz.muni.fi.pa165.legomanager.web.LegoKitActionBean">
                <fieldset><legend><f:message key="kit.list.newpiece"/></legend>
                    <%@include file="form.jsp"%>
                    <br>
                    <s:submit class="button" name="add"><f:message key="kit.create"/></s:submit>
                </fieldset>
            </s:form>
        </div>
    </s:layout-component>
</s:layout-render>