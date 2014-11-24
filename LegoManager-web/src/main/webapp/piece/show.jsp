<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<s:errors/>
<table>
    <tr>
        Color: ${actionBean.legoPieceTO.color}
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
    <c:forEach items="${actionBean.legoPieceTO.legoKits}" var="kit">
        <tr>
            <td>${kit.id}</td>
            <td><c:out value="${kit.name}"/></td>
        </tr>
    </c:forEach>
</table>