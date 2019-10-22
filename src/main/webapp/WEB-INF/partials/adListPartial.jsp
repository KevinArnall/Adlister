<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<ul class="list-unstyled">
    <c:forEach var="ad" items="${ads}">
        <li class="media mb-3">
            <img src="https://via.placeholder.com/64" class="align-self-center mr-3" alt="...">
            <div class="media-body text-truncate">
                <h5 style="width: 50%" class="mt-0 text-truncate"><a href="/ads/ad?id=${ad.id}">${ad.title}</a>
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
                    <c:forEach var="category" items="${ad.categories}">
                        <span class="badge badge-secondary">${category}</span>
                    </c:forEach>
                </p>
            </div>
        </li>
    </c:forEach>
</ul>
