<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<br>
<table class = "basic_prop_table">
    <caption><f:message key="details.basicProperties"/></caption>
    <tr>
        <th><s:label for="s1" name="set.title"/></th>
        <td><s:text id="s1" name="legoSetTO.name"/></td>
    </tr>
    <tr>
        <td></td><td><s:errors field="legoSetTO.name"/></td>
    </tr>
    <tr>
        <th><s:label for="s2" name="set.price"/></th>
        <td><s:text id="s2" name="legoSetTO.price" size="4"/></td>
    </tr>
    <tr>
        <td></td><td><s:errors field="legoSetTO.price"/></td>
    </tr>
</table>
<br>
<table class="basic">
    <caption><f:message key="kit.list.allkits"/></caption>
    <tr>
        <th></th>
        <th>id</th>
        <th><f:message key="kit.name"/></th>                
<th><f:message key="kit.price"/></th>
<th><f:message key="kit.ageRestriction"/></th>                
<th></th>
<th></th>
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
</table>