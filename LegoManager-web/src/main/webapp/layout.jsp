<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<s:layout-definition>
    <!DOCTYPE html>
    <html lang="${pageContext.request.locale}">

        <head>  
            <title><f:message key="${titlekey}"/></title>
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery-ui.css" />

            <script src="${pageContext.request.contextPath}/js/jquery-2.1.1.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/main.js"></script>
            <s:layout-component name="header"/>

        </head>
        <body>
            <s:errors/>    

            <div id="header">
                <img class="header_img" src="${pageContext.request.contextPath}/img/legomanager.png" alt="Lego Logo">
                <f:message key="${titlekey}"/>  
            </div>

            <div id="nav">
                <ul><li><a href="/pa165/"><f:message key="index.home"/></a></li>
                    <li><s:link beanclass="cz.muni.fi.pa165.legomanager.web.LegoSetActionBean"><f:message key="index.sets.link"/></s:link></li>
                    <li><s:link beanclass="cz.muni.fi.pa165.legomanager.web.LegoKitActionBean"><f:message key="index.kits.link"/></s:link></li>
                    <li><s:link beanclass="cz.muni.fi.pa165.legomanager.web.PopulateActionBean">Populate tables for testing</s:link></li>

                        <li><s:link beanclass="cz.muni.fi.pa165.legomanager.web.LegoPieceActionBean"><f:message key="index.pieces.link"/></s:link></li>
                    <li><s:link beanclass="cz.muni.fi.pa165.legomanager.web.CategoryActionBean"><f:message key="index.categories.link"/></s:link></li>
                    </ul>
                </div>

                <div id="content">
                    <span class="bubble">
                    <s:messages/>
                </span>
                <s:layout-component name="body"/>
            </div>

            <div id="footer" >
                <f:message key="index.footer"/>
            </div>


        </body>
    </html>
</s:layout-definition>