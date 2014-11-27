<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="basic_prop_table">
    <caption><f:message key="details.basicProperties"/></caption>
    <tr>
        <th><f:message key="set.name"/></th>
        <td>Name: ${actionBean.legoSetTO.name}</td>
    </tr>
    <tr>
        <th><f:message key="set.price"/></th>
        <td>Price: ${actionBean.legoSetTO.price}</td>
    </tr>
</table>
<br>
<table class="basic">
    <caption><f:message key="set.kits"/></caption>
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
<br>
<table class="basic">
    <caption><f:message key="set.categories"/></caption>
    <tr>
        <th>id</th>
        <th><f:message key="category.name"/></th>
</tr>
<c:forEach items="${actionBean.legoSetTO.categories}" var="category">
    <tr>
        <td>${category.id}</td>
        <td><c:out value="${category.name}"/></td>
    </tr>
</c:forEach>
</table>