<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:errors/>
<table>
    <tr>
        <th><s:label for="s1" name="kit.name"/></th>
        <td><s:text id="s1" name="kit.name"/></td>
    </tr>
    <tr>
        <th><s:label for="s2" name="kit.price"/></th>
        <td><s:text id="s2" name="kit.price" size="4"/></td>
    </tr>
    <tr>
        <th><s:label for="s3" name="kit.agerestr"/></th>
        <td><s:text id="s3" name="kit.agerestr" size="4"/></td>
    </tr>
</table>

<p><f:message key="set.list.allsets"/></p>

<table class="basic">
<s:useActionBean beanclass="cz.muni.fi.pa165.legomanager.web.LegoSetActionBean" var="actionBean2"/>

    <tr>
        <th>id</th>
        <th><f:message key="set.name"/></th>
        <th><f:message key="set.price"/></th>                
        <th></th>
        <th></th>
    </tr>
    <c:forEach items="${actionBean2.legoSets}" var="set">
        <tr>
            <td>${set.id}</td>
            <td><c:out value="${set.name}"/></td>
            <td><c:out value="${set.price}"/></td>                   
        </tr>
    </c:forEach>
</table>