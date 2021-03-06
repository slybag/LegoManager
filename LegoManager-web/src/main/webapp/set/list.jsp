<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="set.list.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.legomanager.web.LegoSetActionBean" var="actionBean"/>
        <c:set var="numberOfSets" scope="session" value="${actionBean.legoSets}"/>
        <div class="menu_container">
        <button class="button dialog_button bigtext" type='button' onclick='create();'><f:message key="set.create"/></button>
        </div>
        <br>
        <div class="table_container">
            <table class="basic">
                <caption><f:message key="set.list.allsets"/></caption>
                <c:choose>
                    <c:when test="${empty numberOfSets}">
                        <tr><td><f:message key="details.noSets"/></td></tr>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <th>id</th>
                            <th><f:message key="set.title"/></th>
                            <th><f:message key="set.price"/></th>                
                            <th></th>
                            <th></th>
                            <th></th>
                        </tr>
                        <c:forEach items="${actionBean.legoSets}" var="set">
                            <tr>
                                <td>${set.id}</td>
                                <td><c:out value="${set.name}"/></td>
                                <td><c:out value="${set.price}"/></td>  
                                <td>
                                    <s:link class="button" beanclass="cz.muni.fi.pa165.legomanager.web.LegoSetActionBean" event="edit"><s:param name="set.id" value="${set.id}"/><f:message key="set.edit"/></s:link>
                                    </td>
                                    <td>
                                    <s:link class="button" beanclass="cz.muni.fi.pa165.legomanager.web.LegoSetActionBean" event="details"><s:param name="set.id" value="${set.id}"/><f:message key="set.details"/></s:link>
                                    </td>
                                    <td>
                                    <s:form beanclass="cz.muni.fi.pa165.legomanager.web.LegoSetActionBean">
                                        <s:hidden name="legoSetTO.id" value="${set.id}"/>
                                        <s:submit class="button" name="delete"><f:message key="set.list.delete"/></s:submit>
                                    </s:form>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </table>
        </div>
        <div class="hidden" id="add" title="Add the set">
            <s:form beanclass="cz.muni.fi.pa165.legomanager.web.LegoSetActionBean">
                <fieldset class="table_container"><legend><f:message key="set.list.newset"/></legend>
                    <%@include file="form.jsp"%>
                    <br>
                    <s:submit class="button" name="add"><f:message key="set.create"/></s:submit>
                    </fieldset>
            </s:form>
        </div>
    </s:layout-component>
</s:layout-render>