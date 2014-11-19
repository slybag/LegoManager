<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<s:errors/>
<table>
    <tr>
        <th><s:label for="p1" name="piece.color"/></th>
        <td><s:select id="p1" name="piece.color"><s:options-enumeration enum="cz.muni.fi.pa165.legomanager.support.Color.PieceColor"/></s:select></td>
    </tr>
    <tr>
        <th><s:label for="p2" name="piece.kits"/></th>
        <td><c:forEach items="piece.legoKits" var="kit"><span>${kit.name}</span></c:forEach></td>
    </tr>
</table>