<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:forEach var="ad" items="${ads}">
    <div class="col-md-4">
        <div class="card mb-3">
            <img src="https://via.placeholder.com/100" class="card-img-top" alt="...">
            <div class="card-body">
                <h5 class="card-title">${ad.title}</h5>
                <p class="card-text">${ad.getDayOfWeekCreated()} ${ad.getMonthCreated()} ${ad.getDayOfMonthCreated()}</p>
                <p class="card-text">${ad.description}</p>
            </div>
            <div class="card-footer">
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
            </div>
        </div>
    </div>
</c:forEach>
