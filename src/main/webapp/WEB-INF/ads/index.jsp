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
            <%--            https://request-inspector.glitch.me/--%>
            <form class="form-inline" method="POST" action="/ads">

                <input name="search" type="text" class="form-control mb-2 mr-sm-2" id="inlineFormInputName2" value="${search}" placeholder="Search">
                <c:choose>
                    <c:when test="${view == 'card'}">
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" checked type="radio" name="view" id="inlineRadio1" value="card">
                            <label class="form-check-label" for="inlineRadio1">Cards</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="view" id="inlineRadio2" value="list">
                            <label class="form-check-label" for="inlineRadio2">List</label>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="view" id="inlineRadio1" value="card">
                            <label class="form-check-label" for="inlineRadio1">Cards</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" checked type="radio" name="view" id="inlineRadio2" value="list">
                            <label class="form-check-label" for="inlineRadio2">List</label>
                        </div>
                    </c:otherwise>
                </c:choose>
                <button type="submit" class="btn btn-primary mb-2 mr-sm-2">Update</button>
            </form>
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
