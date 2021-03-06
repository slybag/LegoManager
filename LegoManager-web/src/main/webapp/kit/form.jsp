<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="numberOfPieces" scope="session" value="${actionBean.legoPieces}"/>

<br>
<table class="basic_prop_table">
    <caption><f:message key="details.basicProperties"/></caption>
    <tr>
        <th><s:label for="s1" name="name"/>:</th>
        <td><s:text id="s1" name="legoKitTO.name"/></td>        
    </tr>
    <tr>
        <td></td><td><s:errors field="legoKitTO.name"/></td>
    </tr>
    <tr>
        <th><s:label for="s2" name="kit.price"/>:</th>
        <td><s:text id="s2" name="legoKitTO.price" size="4"/></td>
    </tr>
    <tr>
        <td></td><td><s:errors field="legoKitTO.price"/></td>
    </tr>
    <tr>
        <th><s:label for="s3" name="kit.agerestr"/>:</th>
        <td><s:text id="s3" name="legoKitTO.ageRestriction" size="4"/></td>
    </tr>
    <tr>
        <td></td><td><s:errors field="legoKitTO.ageRestriction"/></td>
    </tr>
</table>
<br>
<table class="basic">
    <caption><f:message key="set.list.allsets"/></caption>
    <c:choose>
        <c:when test="${empty numberOfPieces}">
            <tr><td><f:message key="details.noPieces"/></td></tr>
        </c:when>
        <c:otherwise>
            <tr>
                <th></th>
                <th>id</th>
                <th><f:message key="piece.color"/></th>                
            </tr>
            <c:forEach items="${actionBean.legoPieces}" var="piece">
                <tr>
                    <td><s:checkbox name="pieceIDs" value="${piece.id}" checked="pieceIDs"/></td>
                    <td>${piece.id}</td>
                    <td><c:out value="${piece.color}"/></td>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</table>