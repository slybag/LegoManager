<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<s:errors/>
<table class="basic_prop_table">
    <caption><f:message key="details.basicProperties"/></caption>
    <tr>
        <th><f:message key="piece.color"/></th>
        <td>${actionBean.legoPieceTO.color}</td>
    </tr>
</table>
<br>
<table class="basic">
    <caption><f:message key="piece.kits"/></caption>
    <tr>
        <th>id</th>
        <th><f:message key="kit.name"/></th>                
        <th><f:message key="kit.price"/></th>
        <th><f:message key="kit.ageRestriction"/></th>
    </tr>
<c:forEach items="${actionBean.legoPieceTO.legoKits}" var="kit">
    <tr>
        <td>${kit.id}</td>           
        <td><c:out value="${kit.name}"/></td>
        <td><c:out value="${kit.price}"/></td>
        <td><c:out value="${kit.ageRestriction}"/></td>
    </tr>
</c:forEach>
</table>