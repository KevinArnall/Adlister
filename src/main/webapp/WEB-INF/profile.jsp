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

        <div class="container mt-5">
            <h1>Welcome, ${sessionScope.user.username}!</h1>
            <h1>Here Are all your ads!</h1>
            <div class="row ">
                <c:forEach var="ad" items="${ads}">
                    <div class="col-md-4">
                        <div class="card mb-3">
                            <div class="card-header">
                                <h4 class="card-title">${ad.title}</h4>
                            </div>
                            <div class="card-body">
                                <p class="card-text">${ad.description}</p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </body>
</html>
