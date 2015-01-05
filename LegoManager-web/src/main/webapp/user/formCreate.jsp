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
        <td><s:text id="s1" name="userTO.accountName"/></td>
    </tr>
    <tr>
        <th><s:label for="s2" name="user.password"/></th>
        <td><s:text id="s2" name="userTO.password"/></td>
    </tr>   
    <tr>
        <th><s:label for="s2" name="user.role"/></th>
        <td><s:text id="s2" name="userTO.role"/></td>
    </tr> 
</table>
