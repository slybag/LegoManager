<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<s:errors/>
<table>
    <tr>
        Name: ${actionBean.categoryTO.name}
    </tr>
    <tr>
        Price: ${actionBean.categoryTO.description}
    </tr>
</table>

<table class="basic">
    <tr>
        <th>id</th>
        <th><f:message key="set.name"/></th>
<th><f:message key="set.price"/></th>                
<th></th>
<th></th>
</tr>
<c:forEach items="${actionBean.categoryTO.legoSets}" var="set">
    <tr>
        <td>${set.id}</td>
        <td><c:out value="${set.name}"/></td>
    <td><c:out value="${set.price}"/></td>                   
    </tr>
</c:forEach>
</table>
<table class="basic">
    <tr>
        <th></th>
        <th>id</th>
        <th><f:message key="kit.name"/></th>                
<th><f:message key="kit.price"/></th>
<th><f:message key="kit.ageRestriciton"/></th>                
<th></th>
<th></th>
</tr>
<c:forEach items="${actionBean.categoryTO.legoKits}" var="kit">
    <tr>
        <td>${kit.id}</td>           
        <td><c:out value="${kit.name}"/></td>
        <td><c:out value="${kit.price}"/></td>
        <td><c:out value="${kit.ageRestriction}"/></td>
    </tr>
</c:forEach>
</table>