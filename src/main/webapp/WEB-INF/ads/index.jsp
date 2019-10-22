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
                <div class="form-check form-check-inline">
                    <c:choose>
                        <c:when test="${view == 'card'}">
                            <input class="form-check-input" checked type="radio" name="view" id="inlineRadio1" value="card">
                        </c:when>
                        <c:otherwise>
                            <input class="form-check-input" type="radio" name="view" id="inlineRadio1" value="card">
                        </c:otherwise>
                    </c:choose>
                    <label class="form-check-label" for="inlineRadio1">Cards</label>
                </div>
                <div class="form-check form-check-inline">
                    <c:choose>
                        <c:when test="${view == 'list'}">
                            <input class="form-check-input" checked type="radio" name="view" id="inlineRadio2" value="list">
                        </c:when>
                        <c:otherwise>
                            <input class="form-check-input" type="radio" name="view" id="inlineRadio2" value="list">
                        </c:otherwise>
                    </c:choose>
                    <label class="form-check-label" for="inlineRadio2">List</label>
                </div>
                <div class="form-check">
                    <c:choose>
                        <c:when test="${filter.contains('Help Wanted')}">
                            <input name="categories" class="form-check-input" type="checkbox" value="Help Wanted" id="cat1" checked>
                        </c:when>
                        <c:otherwise>
                            <input name="categories" class="form-check-input" type="checkbox" value="Help Wanted" id="cat1">
                        </c:otherwise>
                    </c:choose>
                    <label class="form-check-label" for="cat1">
                        Help Wanted
                    </label>
                </div>
                <div class="form-check">
                    <c:choose>
                        <c:when test="${filter.contains('For Sale')}">
                            <input name="categories" class="form-check-input" type="checkbox" value="For Sale" id="cat2" checked>
                        </c:when>
                        <c:otherwise>
                            <input name="categories" class="form-check-input" type="checkbox" value="For Sale" id="cat2">
                        </c:otherwise>
                    </c:choose>
                    <label class="form-check-label" for="cat2">
                        For Sale
                    </label>
                </div>
                <input name="query" type="hidden" value="${query}">
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
