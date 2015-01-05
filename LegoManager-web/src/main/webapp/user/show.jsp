<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="basic_prop_table">
    <caption><f:message key="details.basicProperties"/></caption>
    <tr>
        <th><f:message key="user.accountName"/></th>
        <td>Name: ${actionBean.userTO.accountName}</td>
    </tr>
    <tr>
        <th><f:message key="user.password"/></th>
        <td>Pass: ${actionBean.userTO.password}</td>
    </tr>
    <tr>
        <th><f:message key="user.role"/></th>
        <td>Role: ${actionBean.userTO.role}</td>
    </tr>
</table>
