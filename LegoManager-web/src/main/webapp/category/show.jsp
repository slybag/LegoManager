<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<s:errors/>
<table class="basic_prop_table">
    <caption><f:message key="details.basicProperties"/></caption>
    <tr>
        <th><f:message key="name"/></th>
        <td>${actionBean.categoryTO.name}</td>
    </tr>
    <tr>
        <th><f:message key="category.description"/></th>
        <td>${actionBean.categoryTO.description}</td>
    </tr>
</table>
<br>
<table class="basic">
    <caption><f:message key="category.sets"/></caption>
    <tr>
        <th>id</th>
        <th><f:message key="set.name"/></th>
        <th><f:message key="set.price"/></th>             
    </tr>
<c:forEach items="${actionBean.categoryTO.legoSets}" var="set">
    <tr>
        <td>${set.id}</td>
        <td><c:out value="${set.name}"/></td>
        <td><c:out value="${set.price}"/></td>                   
    </tr>
</c:forEach>
</table>
<br>
<table class="basic">
    <caption><f:message key="category.kits"/></caption>
    <tr>
        <th>id</th>
        <th><f:message key="kit.name"/></th>                
        <th><f:message key="kit.price"/></th>
        <th><f:message key="kit.ageRestriction"/></th>
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