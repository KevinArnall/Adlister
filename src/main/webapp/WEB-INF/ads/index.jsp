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

        <div class="container">
            <h1>Here Are all the ads!</h1>
            <div class="row mt-5">
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
