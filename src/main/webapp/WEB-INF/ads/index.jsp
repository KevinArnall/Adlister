<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <jsp:include page="/WEB-INF/partials/head.jsp">
            <jsp:param name="title" value="Viewing All The Ads"/>
        </jsp:include>
    </head>
    <body>
        <jsp:include page="/WEB-INF/partials/navbar.jsp"/>
        <div class="container mt-5 pt-5">
            <h1>Here Are all the ads!</h1>
            <div class="row">
                <jsp:include page="/WEB-INF/partials/partialAd.jsp"/>
            </div>
        </div>
    </body>
</html>
