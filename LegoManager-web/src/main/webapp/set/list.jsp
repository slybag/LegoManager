<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="set.list.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.legomanager.web.LegoSetActionBean" var="actionBean"/>

        <p><f:message key="set.list.allsets"/></p>

        <table class="basic">
            <tr>
                <th>id</th>
                <th><f:message key="set.title"/></th>
                <th><f:message key="set.price"/></th>                
                <th></th>
                <th></th>
            </tr>
            <c:forEach items="${actionBean.legoSets}" var="set">
                <tr>
                    <td>${set.id}</td>
                    <td><c:out value="${set.name}"/></td>
                    <td><c:out value="${set.price}"/></td>                    
                    <td>
                     <s:link beanclass="cz.muni.fi.pa165.legomanager.web.LegoSetActionBean" event="edit"><s:param name="set.id" value="${set.id}"/>edit</s:link>
                    </td>
                    <td>
                        <s:form beanclass="cz.muni.fi.pa165.legomanager.web.LegoSetActionBean">
                            <s:hidden name="legoSetTO.id" value="${set.id}"/>
                            <s:submit name="delete"><f:message key="set.list.delete"/></s:submit>
                        </s:form>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <s:form beanclass="cz.muni.fi.pa165.legomanager.web.LegoSetActionBean">
            <fieldset><legend><f:message key="set.list.newset"/></legend>
                <%@include file="form.jsp"%>
                <s:submit name="add">Vytvořit novou knihu</s:submit>
            </fieldset>
        </s:form>
    </s:layout-component>
</s:layout-render>