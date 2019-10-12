<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.time.format.TextStyle" %>
<%@ page import="java.util.Locale" %>

<c:forEach var="ad" items="${ads}">
    <div class="col-md-4">
        <div class="card mb-3">
            <img src="https://via.placeholder.com/100" class="card-img-top" alt="...">
            <div class="card-body">
                <h5 class="card-title">${ad.title}</h5>
                <p class="card-text">${ad.dateCreated.getDayOfWeek().getDisplayName(TextStyle.SHORT,Locale.ENGLISH)} ${ad.dateCreated.getMonth().getDisplayName(TextStyle.SHORT,Locale.ENGLISH)} ${ad.dateCreated.getDayOfMonth()}</p>
                <p class="card-text">${ad.description}</p>
            </div>
            <div class="card-footer">
                <c:choose>
                    <c:when test="${ad.getTimeSinceCreation().abs().toHours() > 24}">
                        <small class="text-muted">Posted ${ad.getTimeSinceCreation().abs().toDays()} days ago </small>
                    </c:when>
                    <c:when test="${ad.getTimeSinceCreation().abs().toMinutes() > 60}">
                        <small class="text-muted">Posted ${ad.getTimeSinceCreation().abs().toHours()} hours ago </small>
                    </c:when>
                    <c:otherwise>
                        <small class="text-muted">Posted ${ad.getTimeSinceCreation().abs().toMinutes()} minutes ago </small>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</c:forEach>
