<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<s:layout-definition>
<!DOCTYPE html>
<html lang="${pageContext.request.locale}">
<head>
  <title><f:message key="${titlekey}"/></title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css" />
  <s:layout-component name="header"/>
</head>
<body>
   <h1><f:message key="${titlekey}"/></h1>

   <div id="content">
       <s:messages/>
       <s:layout-component name="body"/>
    </div>
</body>
</html>
</s:layout-definition>