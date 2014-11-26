<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:errors/>
<table>
    <tr>
        <th><s:label for="s1" name="category.name"/></th>
        <td><s:text id="s1" name="categoryTO.name"/></td>
    </tr>
    <tr>
        <th><s:label for="s2" name="category.description"/></th>
        <td><s:text id="s2" name="categoryTO.description"/></td>
    </tr> 
</table>
<table class="basic">
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
</table>
<table class="basic">
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
</table>