<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="piece.list.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.legomanager.web.LegoPieceActionBean" var="actionBean"/>

        <p><f:message key="piece.list.allpieces"/></p>
        <button type='button' onclick='alert("create piece!");'>Create Piece</button>
        <table class="basic">
            <tr>
                <th>id</th>
                <th><f:message key="piece.color"/></th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach items="${actionBean.legoPieces}" var="piece">
                <tr>
                    <td>${piece.id}</td>
                    <td><c:out value="${piece.color}"/></td>
                    <td>
                    <s:link beanclass="cz.muni.fi.pa165.legomanager.web.LegoPieceActionBean" event="edit"><s:param name="piece.id" value="${piece.id}"/>edit</s:link>
                    </td>
                    <td>
                    <s:link beanclass="cz.muni.fi.pa165.legomanager.web.LegoPieceActionBean" event="details"><s:param name="piece.id" value="${piece.id}"/>details</s:link>
                    </td>
                    <td>
                    <s:form beanclass="cz.muni.fi.pa165.legomanager.web.LegoPieceActionBean">
                        <s:hidden name="legoPieceTO.id" value="${piece.id}"/>
                        <s:submit name="delete"><f:message key="piece.list.delete"/></s:submit>
                    </s:form>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <s:form beanclass="cz.muni.fi.pa165.legomanager.web.LegoPieceActionBean">
            <fieldset><legend><f:message key="piece.list.newpiece"/></legend>
                <%@include file="form.jsp"%>
                <s:submit name="add"><f:message key="piece.create.newpiece"/></s:submit>
                </fieldset>
        </s:form>
    </s:layout-component>
</s:layout-render>