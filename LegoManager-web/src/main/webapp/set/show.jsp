<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<s:errors/>
<table>
    <tr>
        <td>Name: ${actionBean.legoSetTO.name}</td>
    </tr>
    <tr>
        <td>Price: ${actionBean.legoSetTO.price}</td>
    </tr>
</table>
<f:message key="set.kits"/>
<table class="basic">
    <tr>
        <th>id</th>
        <th><f:message key="kit.name"/></th>                
        <th><f:message key="kit.price"/></th>
        <th><f:message key="kit.ageRestriction"/></th>
    </tr>
<c:forEach items="${actionBean.legoSetTO.legoKits}" var="kit">
    <tr>
        <td>${kit.id}</td>           
        <td><c:out value="${kit.name}"/></td>
        <td><c:out value="${kit.price}"/></td>
        <td><c:out value="${kit.ageRestriction}"/></td>
    </tr>
</c:forEach>
</table>