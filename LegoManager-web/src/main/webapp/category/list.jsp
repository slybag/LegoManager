<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="category.list.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.legomanager.web.CategoryActionBean" var="actionBean"/>

        <p><f:message key="category.list.allcategories"/></p>

        <table class="basic">
            <tr>
                <th>id</th>
                <th><f:message key="category.name"/></th>
                <th><f:message key="category.description"/></th>
                <th><f:message key="category.kits"/></th>
                <th><f:message key="category.sets"/></th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach items="${actionBean.categories}" var="category">
                <tr>
                    <td>${category.id}</td>
                    <td><c:out value="${category.name}"/></td>
                    <td><c:out value="${category.description}"/></td>
                    <th><c:forEach items="category.legoKits" var="kit"><span>kit.name</span></c:forEach></th>
                    <th><c:forEach items="category.legoSets" var="set"><span>set.name</span></c:forEach></th>
                        <td>
                        <s:link beanclass="cz.muni.fi.pa165.legomanager.web.CategoryActionBean" event="edit"><s:param name="category.id" value="${category.id}"/>edit</s:link>
                        </td>
                        <td>
                        <s:form beanclass="cz.muni.fi.pa165.legomanager.web.CategoryActionBean">
                            <s:hidden name="categoryTO.id" value="${category.id}"/>
                            <s:submit name="delete"><f:message key="category.list.delete"/></s:submit>
                        </s:form>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <s:form beanclass="cz.muni.fi.pa165.legomanager.web.CategoryActionBean">
            <fieldset><legend><f:message key="category.list.newcategory"/></legend>
                <%@include file="form.jsp"%>
                <s:submit name="add"><f:message key="category.create.newcategory"/></s:submit>
                </fieldset>
        </s:form>
    </s:layout-component>
</s:layout-render>