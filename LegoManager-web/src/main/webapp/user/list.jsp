<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="user.list.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.legomanager.web.UserActionBean" var="actionBean"/>
        <c:set var="numberOfUsers" scope="session" value="${actionBean.users}"/>
        <div class="menu_container">
            <button class="button dialog_button bigtext" type='button' onclick='create();'><f:message key="user.create"/></button>
        </div>
        <br>
        <div class="table_container">
            <table class="basic">
                <caption><f:message key="user.list.allusers"/></caption>
                <c:choose>
                    <c:when test="${empty numberOfUsers}">
                        <tr><td><f:message key="details.noUsers"/></td></tr>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <th><f:message key="user.accountName"/></th>
                            <th><f:message key="user.password"/></th>                
                            <th><f:message key="user.role"/></th>
                            <th></th>
                            <th></th>
                            <th></th>
                        </tr>
                        <c:forEach items="${actionBean.users}" var="user">
                            <tr>
                                <td><c:out value="${user.accountName}"/></td>
                                <td><c:out value="${user.password}"/></td>  
                                <td><c:out value="${user.role}"/></td>  
                                <td>
                                    <s:link class="button" beanclass="cz.muni.fi.pa165.legomanager.web.UserActionBean" event="edit"><s:param name="user.accountName" value="${user.accountName}"/><f:message key="user.edit"/></s:link>
                                    </td>
                                <td>
                                    <s:link class="button" beanclass="cz.muni.fi.pa165.legomanager.web.UserActionBean" event="details"><s:param name="user.accountName" value="${user.accountName}"/><f:message key="user.details"/></s:link>
                                    </td>
                                <td>
                                    <s:form beanclass="cz.muni.fi.pa165.legomanager.web.UserActionBean">
                                        <s:hidden name="userTO.accountName" value="${user.accountName}"/>
                                        <s:submit class="button" name="delete"><f:message key="user.list.delete"/></s:submit>
                                    </s:form>
                                    </td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </table>
        </div>
        <div class="hidden" id="add" title="Add the user">
            <s:form beanclass="cz.muni.fi.pa165.legomanager.web.UserActionBean">
                <fieldset class="table_container"><legend><f:message key="user.list.newuser"/></legend>
                        <%@include file="formCreate.jsp"%>
                    <br>
                    <s:submit class="button" name="add"><f:message key="user.create"/></s:submit>
                    </fieldset>
            </s:form>
        </div>
    </s:layout-component>
</s:layout-render>