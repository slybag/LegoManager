<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<s:errors/>
<table>
    <tr>
        <td>Name: ${actionBean.legoKitTO.name}</td>
    </tr>
    <tr>
        <td>Price: ${actionBean.legoKitTO.price}</td>
    </tr>
    <tr>
        <td>Age Restriction ${actionBean.legoKitTO.ageRestriction}</td>
    </tr>
</table>
<f:message key="kit.pieces"/>
<table class="basic">
    <tr>
        <th>id</th>
        <th><f:message key="piece.color"/></th>
    </tr>
    <c:forEach items="${actionBean.legoKitTO.legoPieces}" var="piece">
        <tr>
            <td>${piece.id}</td>
            <td><c:out value="${piece.color}"/></td>
        </tr>
    </c:forEach>
</table>
<f:message key="kit.sets"/>
<table class="basic">
    <tr>
        <th>id</th>
        <th><f:message key="set.name"/></th>
        <th><f:message key="set.price"/></th>      
</tr>
<c:forEach items="${actionBean.legoKitTO.legoSets}" var="set">
    <tr>
        <td>${set.id}</td>
        <td><c:out value="${set.name}"/></td>
        <td><c:out value="${set.price}"/></td>                   
    </tr>
</c:forEach>
</table>
<f:message key="kit.categories"/>
<table class="basic">
    <tr>
        <th>id</th>
        <th><f:message key="category.name"/></th>
</tr>
<c:forEach items="${actionBean.legoKitTO.categories}" var="category">
    <tr>
        <td>${category.id}</td>
        <td><c:out value="${category.name}"/></td>
    </tr>
</c:forEach>
</table>