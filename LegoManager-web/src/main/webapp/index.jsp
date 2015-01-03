<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<s:layout-render name="/layout.jsp" titlekey="index.title">
    <s:layout-component name="body">
        <h2>Welcome to the Lego Manager storage system.</h2> <br><br>
        <sec:authorize access="isAnonymous()">
        If you want to login as administrator use this credentials: <br><br>
        <b>Username: admin</b><br>
        <b>Password: admin</b><br><br>
        If you want to login as a regular system user use this login<br><br>
        <b>Username: rest</b><br>
        <b>Password: rest</b><br><br>
        </sec:authorize>
        <sec:authorize url="/all">
           You are now logged in as <b><sec:authentication property="principal.username" /></b>.
        </sec:authorize>
    </s:layout-component>
</s:layout-render>