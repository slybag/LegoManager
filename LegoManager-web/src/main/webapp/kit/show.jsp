<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:set var="piecesInKit" scope="session" value="${actionBean.legoKitTO.legoPieces}"/>
    <c:set var="setsInKit" scope="session" value="${actionBean.legoKitTO.legoSets}"/>
    <c:set var="categoriesInKit" scope="session" value="${actionBean.legoKitTO.categories}"/>

<table class="basic_prop_table">
    <caption><f:message key="details.basicProperties"/></caption>
    <tr>
        <th><f:message key="kit.name"/>:</th>
        <td>${actionBean.legoKitTO.name}</td>
    </tr>
    <tr>
        <th><f:message key="kit.price"/>:</th>
        <td>${actionBean.legoKitTO.price}</td>
    </tr>
    <tr>
        <th><f:message key="kit.ageRestriction"/>:</th>
        <td>${actionBean.legoKitTO.ageRestriction}</td>
    </tr>
</table>
 <br>
<table class="basic">
    <caption><f:message key="kit.pieces"/></caption>
    <c:choose>
        <c:when test="${empty piecesInKit}">
            <tr><td><f:message key="details.noPieces"/></td></tr>
        </c:when>
        <c:otherwise>
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
        </c:otherwise>
    </c:choose>
</table>
 <br>
<table class="basic">
    <caption><f:message key="kit.sets"/></caption>
    <c:choose>
        <c:when test="${empty setsInKit}">
            <tr><td><f:message key="details.noSets"/></td></tr>
        </c:when>
        <c:otherwise>
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
        </c:otherwise>
    </c:choose>
</table>
 <br>
<table class="basic">
    <caption><f:message key="kit.categories"/></caption>
    <c:choose>
        <c:when test="${empty categoriesInKit}">
            <tr><td><f:message key="details.noCategories"/></td></tr>
        </c:when>
        <c:otherwise>
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
        </c:otherwise>
    </c:choose>
</table>