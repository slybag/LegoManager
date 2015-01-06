<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <s:errors/>
</div>
<br>
<br>
<table class = "basic_prop_table">
    <caption><f:message key="details.basicProperties"/></caption>
    <tr>
        <th><s:label for="s1" name="user.accountName"/></th>
        <td><s:text id="s1" name="userTO.accountName" disabled="true"/></td>
    </tr>
    <tr>
        <th><s:label for="s2" name="newPassword"/></th>
        <td><s:text id="s2" name="newPassword" /></td>
    </tr>
    <tr>
        <th><s:label for="s2" name="user.role"/></th>
        <td><s:select id="s2" name="userTO.role"><s:options-enumeration enum="cz.muni.fi.pa165.legomanager.support.UserRole"/></s:select></td>
    </tr> 
</table>
<s:hidden name="userTO.password" value="${user.password}"/>

