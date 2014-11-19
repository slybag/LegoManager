<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<s:errors/>
<table>
    <tr>
        <th><s:label for="p1" name="piece.color"/></th>
        <td><s:select id="p1" name="legoPieceTO.color"><s:options-enumeration enum="cz.muni.fi.pa165.legomanager.support.Color.PieceColor"/></s:select></td>
    </tr>
</table>