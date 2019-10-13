<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <jsp:include page="/WEB-INF/partials/head.jsp">
            <jsp:param name="title" value="Your Profile"/>
        </jsp:include>
    </head>
    <body>
        <jsp:include page="/WEB-INF/partials/navbar.jsp"/>

        <div class="container mt-5 pt-5">
            <h1>Welcome, ${sessionScope.user.username}!</h1>
            <h1>Here Are all your ads!</h1>
            <jsp:include page="/WEB-INF/partials/adListPartial.jsp.jsp"/>
        </div>
    </body>
</html>
