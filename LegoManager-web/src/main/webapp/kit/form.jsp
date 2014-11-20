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
        <th>id</th>
        <th><f:message key="set.name"/></th>
        <th><f:message key="set.price"/></th>                
        <th></th>
        <th></th>
    </tr>
    <c:forEach items="${actionBean.legoSets}" var="set">
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
        <th><f:message key="category.description"/></th>                
        <th></th>
        <th></th>
    </tr>
    <c:forEach items="${actionBean.categories}" var="category">
        <tr>
            <td><s:checkbox name="categoryIDs" value="${category.id}" checked="categoryIDs"/></td>
            <td>${category.id}</td>
            <td><c:out value="${category.name}"/></td>
            <td><c:out value="${category.description}"/></td>
        </tr>
    </c:forEach>
</table>
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