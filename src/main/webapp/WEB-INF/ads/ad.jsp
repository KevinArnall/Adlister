<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <jsp:include page="/WEB-INF/partials/head.jsp">
            <jsp:param name="title" value="${ad.title}"/>
        </jsp:include>
    </head>
    <body>
        <jsp:include page="/WEB-INF/partials/navbar.jsp"/>
        <div class="container mt-5 pt-5">
            <h3>${ad.title}</h3>
            <div class="row">
                <div class="col overflow-auto">
                    <p>Posted on: ${ad.getDayOfWeekCreated()} ${ad.getMonthCreated()} ${ad.getDayOfMonthCreated()}</p>
                    <p>By: ${user.username}</p>
                    <p>${ad.description}</p>
                    <c:if test="${user.username.equals(loggedinuser.username)}">
                        <a class="btn btn-primary" href="/ads/edit?id=${ad.id}">Edit</a>
                    </c:if>
                </div>
                <div class="col text-center">
                    <img src="https://via.placeholder.com/300" class="img-thumbnail" alt="...">
                </div>
            </div>
            <div>
                <c:choose>
                    <c:when test="${ad.getHoursSinceCreation() >= 24}">
                        <small class="text-muted">Posted ${ad.getDaysSinceCreation()} days ago </small>
                    </c:when>
                    <c:when test="${ad.getMinutesSinceCreation() >= 60}">
                        <small class="text-muted">Posted ${ad.getHoursSinceCreation()} hours ago </small>
                    </c:when>
                    <c:when test="${ad.getSecondsSinceCreation() >= 60}">
                        <small class="text-muted">Posted ${ad.getMinutesSinceCreation()} minutes ago </small>
                    </c:when>
                    <c:otherwise>
                        <small class="text-muted">Posted ${ad.getSecondsSinceCreation()} seconds ago </small>
                    </c:otherwise>
                </c:choose>
                <c:forEach var="category" items="${ad.categories}">
                    <span class="badge badge-secondary">${category}</span>
                </c:forEach>
            </div>
        </div>
    </body>
</html>
