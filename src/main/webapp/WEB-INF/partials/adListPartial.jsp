<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<ul class="list-unstyled">
    <c:forEach var="ad" items="${ads}">
        <li class="media mb-3">
            <img src="https://via.placeholder.com/64" class="align-self-start mr-3" alt="...">
            <div class="media-body">
                <h5 class="mt-0"><a href="/ads/ad?id=${ad.id}">${ad.title}</a>
                    <c:forEach var="category" items="${ad.categories}">
                        <a class="badge badge-primary" href="?filter=${category}">${category}</a>
                    </c:forEach>
                </h5>
                    ${ad.description}
                <p>
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
                </p>
            </div>
        </li>
    </c:forEach>
</ul>
