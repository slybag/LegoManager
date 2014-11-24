<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<s:errors/>
<table>
    <tr>
        <th><s:label for="s1" name="kit.name"/></th>
        <td><s:text id="s1" name="legoKitTO.name"/></td>
    </tr>
    <tr>
        <th><s:label for="s2" name="kit.price"/></th>
        <td><s:text id="s2" name="legoKitTO.price" size="4"/></td>
    </tr>
    <tr>
        <th><s:label for="s3" name="kit.agerestr"/></th>
        <td><s:text id="s3" name="legoKitTO.ageRestriction" size="4"/></td>
    </tr>
</table>
<p><f:message key="set.list.allsets"/></p>

<table class="basic">
    <tr>
        <th></th>
        <th>id</th>
        <th><f:message key="piece.color"/></th>                
        <th></th>
        <th></th>
    </tr>
    <c:forEach items="${actionBean.legoPieces}" var="piece">
        <tr>
            <td><s:checkbox name="pieceIDs" value="${piece.id}" checked="pieceIDs"/></td>
            <td>${piece.id}</td>
            <td><c:out value="${piece.color}"/></td>
        </tr>
    </c:forEach>
</table>