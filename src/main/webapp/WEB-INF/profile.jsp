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
            <h1>Welcome, <c:out value="${sessionScope.user.username}"/>!</h1>
            <h1>Here are all the ads you have created</h1>
            <c:choose>
                <c:when test="${view =='list'}">
                    <jsp:include page="/WEB-INF/partials/adListPartial.jsp"/>
                </c:when>
                <c:when test="${view == 'card'}">
                    <jsp:include page="/WEB-INF/partials/adCardPartial.jsp"/>
                </c:when>
            </c:choose>
        </div>
    </body>
</html>
