<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<s:errors/>
<table>
    <tr>
        Name: ${actionBean.legoKitTO.name}
    </tr>
    <tr>
        Price: ${actionBean.legoKitTO.price}
    </tr>
    <tr>
        Age Restriction ${actionBean.legoKitTO.ageRestriction}
    </tr>
</table>

<table class="basic">
    <tr>
        <th></th>
        <th>id</th>
        <th><f:message key="piece.color"/></th>                
        <th></th>
        <th></th>
    </tr>
    <c:forEach items="${actionBean.legoKitTO.legoPieces}" var="piece">
        <tr>
            <td>${piece.id}</td>
            <td><c:out value="${piece.color}"/></td>
        </tr>
    </c:forEach>
</table>
<table class="basic">
    <tr>
        <th>id</th>
        <th><f:message key="set.name"/></th>
<th><f:message key="set.price"/></th>                
<th></th>
<th></th>
</tr>
<c:forEach items="${actionBean.legoKitTO.legoSets}" var="set">
    <tr>
        <td><s:checkbox name="setIDs" value="${set.id}" checked="setIDs"/></td>
        <td>${set.id}</td>
        <td><c:out value="${set.name}"/></td>
    <td><c:out value="${set.price}"/></td>                   
    </tr>
</c:forEach>
</table>
<table class="basic">
    <tr>
        <th>id</th>
        <th><f:message key="category.name"/></th>
        <th></th>
        <th></th>
</tr>
<c:forEach items="${actionBean.legoKitTO.categories}" var="category">
    <tr>
        <td>${category.id}</td>
        <td><c:out value="${category.name}"/></td>
    </tr>
</c:forEach>
</table>