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
            <div class="row">
                <div class="col-3">
                    <div class="card">
                        <div class="card-header text-center">Options</div>
                        <div class="card-body">
                            <form class="mb-0" method="POST" action="/ads">
                                <div class="form-group">
                                    <input name="search" type="text" class="form-control" id="inlineFormInputName2" value="${search}" placeholder="Search">
                                </div>
                                <div class="form-group">
                                    <p class="card-title">View</p>
                                    <div class="form-check">
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
                                    <div class="form-check">
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
                                </div>
                                <div class="form-group">
                                    <p class="card-title">Filter</p>
                                    <div class="form-check">
                                        <c:choose>
                                            <c:when test="${filter.contains('Help Wanted')}">
                                                <input name="categories" class="form-check-input" type="checkbox" value="Help Wanted" id="cat1" checked>
                                            </c:when>
                                            <c:otherwise>
                                                <input name="categories" class="form-check-input" type="checkbox" value="Help Wanted" id="cat1">
                                            </c:otherwise>
                                        </c:choose>
                                        <label class="form-check-label" for="cat1">Help Wanted</label>
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
                                        <label class="form-check-label" for="cat2">For Sale</label>
                                    </div>
                                </div>
                                <div class="text-center">
                                    <button type="submit" class="btn btn-primary">Apply</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="col-9">
                    <c:choose>
                        <c:when test="${view =='list'}">
                            <jsp:include page="/WEB-INF/partials/adListPartial.jsp"/>
                        </c:when>
                        <c:when test="${view == 'card'}">
                            <jsp:include page="/WEB-INF/partials/adCardPartial.jsp"/>
                        </c:when>
                    </c:choose>
                </div>
            </div>
        </div>
    </body>
</html>
