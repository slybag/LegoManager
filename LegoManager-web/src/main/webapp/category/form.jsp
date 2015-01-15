<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<c:set var="numberOfSets" scope="session" value="${actionBean.legoSets}"/>
<c:set var="numberOfKits" scope="session" value="${actionBean.legoKits}"/>

<br>
<table class="basic_prop_table">
    <caption><f:message key="details.basicProperties"/></caption>
    <tr>
        <th><s:label for="s1" name="name"/>:</th>
        <td><s:text id="s1" name="categoryTO.name"/></td>
    </tr>
    <tr>
        <td></td><td><s:errors field="categoryTO.name"/></td>
    </tr>
    <tr>
        <th><s:label for="s2" name="category.description"/>:</th>
        <td><s:textarea id="s2" name="categoryTO.description"/></td>
    </tr>
    <tr>
        <td></td><td><s:errors field="categoryTO.description"/></td>
    </tr>
</table>
<br>
<table class="basic">
    <caption><f:message key="list.set.allsets"/></caption>
    <c:choose>
        <c:when test="${empty numberOfSets}">
            <tr><td><f:message key="details.noSets"/></td></tr>
        </c:when>
        <c:otherwise>
            <tr>
                <th></th>
                <th>id</th>
                <th><f:message key="set.name"/></th>
                <th><f:message key="set.price"/></th>
            </tr>
            <c:forEach items="${actionBean.legoSets}" var="set">
                <tr>
                    <td><s:checkbox name="setIDs" value="${set.id}" checked="setIDs"/></td>
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
    <caption><f:message key="list.kit.allkits"/></caption>
    <c:choose>
        <c:when test="${empty numberOfKits}">
            <tr><td><f:message key="details.noKits"/></td></tr>
        </c:when>
        <c:otherwise>
            <tr>
                <th></th>
                <th>id</th>
                <th><f:message key="kit.name"/></th>                
                <th><f:message key="kit.price"/></th>
                <th><f:message key="kit.ageRestriction"/></th> 
            </tr>
            <c:forEach items="${actionBean.legoKits}" var="kit">
                <tr>
                    <td><s:checkbox name="kitIDs" value="${kit.id}" checked="kitIDs"/></td>
                    <td>${kit.id}</td>           
                    <td><c:out value="${kit.name}"/></td>
                    <td><c:out value="${kit.price}"/></td>
                    <td><c:out value="${kit.ageRestriction}"/></td>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</table>