<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <jsp:include page="/WEB-INF/partials/head.jsp">
            <jsp:param name="title" value="Edit an Ad"/>
        </jsp:include>
    </head>
    <body>
        <jsp:include page="/WEB-INF/partials/navbar.jsp"/>
        <div class="container">
            <div class="row align-items-center justify-content-center vh-100">
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-body text-center">
                            <h1 class="card-title">Edit an ad</h1>
                            <form action="/ads/edit" method="post">
                                <%--                                                                <form action="https://request-inspector.glitch.me/" method="post">--%>
                                <div class="form-group">
                                    <label for="title">Title</label>
                                    <c:choose>
                                        <c:when test="${needcat}">
                                            <input maxlength="100" id="title" name="title" class="form-control" type="text" value="${title}" required>
                                        </c:when>
                                        <c:otherwise>
                                            <input maxlength="100" id="title" name="title" class="form-control" type="text" value="${ad.title}" required>
                                        </c:otherwise>
                                    </c:choose>
                                    <input type="hidden" name="id" value="${ad.id}">
                                </div>
                                <div class="form-group">
                                    <label for="description">Description</label>
                                    <c:choose>
                                        <c:when test="${needcat}">
                                            <textarea maxlength="500" id="description" name="description" class="form-control" required>${description}</textarea>
                                        </c:when>
                                        <c:otherwise>
                                            <textarea maxlength="500" id="description" name="description" class="form-control" required>${ad.description}</textarea>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div class="form-group">
                                    <label>Categories</label>
                                    <c:choose>
                                        <c:when test="${needcat}">
                                            <div class="form-check">
                                                <input class="form-check-input is-invalid" name="categories" type="checkbox" id="inlineCheckbox1" value="For Sale">
                                                <label class="form-check-label" for="inlineCheckbox1">For sale</label>
                                            </div>
                                            <div class="form-check">
                                                <input class="form-check-input is-invalid" name="categories" type="checkbox" id="inlineCheckbox2" value="Help Wanted">
                                                <label class="form-check-label" for="inlineCheckbox2">Help Wanted</label>
                                                <div class="invalid-feedback">
                                                    Please select at least 1 category
                                                </div>
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="form-check">
                                                <input
                                                        <c:if test="${forsale}">checked</c:if> class="form-check-input" name="categories" type="checkbox" id="inlineCheckbox1" value="For Sale">
                                                <label class="form-check-label" for="inlineCheckbox1">For sale</label>
                                            </div>
                                            <div class="form-check">
                                                <input
                                                        <c:if test="${helpwanted}">checked</c:if> class="form-check-input" name="categories" type="checkbox" id="inlineCheckbox2" value="Help Wanted">
                                                <label class="form-check-label" for="inlineCheckbox2">Help Wanted</label>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <button type="submit" class="btn btn-block btn-primary">Submit</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
