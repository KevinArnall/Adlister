<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <jsp:include page="/WEB-INF/partials/head.jsp">
            <jsp:param name="title" value="Create a new Ad"/>
        </jsp:include>
    </head>
    <body>
        <jsp:include page="/WEB-INF/partials/navbar.jsp"/>
        <div class="container">
            <div class="row align-items-center justify-content-center vh-100">
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-body text-center">
                            <h1 class="card-title">Create a new ad</h1>
                            <form action="/ads/create" method="post">
                                <%--                                                                <form action="https://request-inspector.glitch.me/" method="post">--%>
                                <div class="form-group">
                                    <label for="title">Title</label>
                                    <input maxlength="100" id="title" name="title" class="form-control" type="text" value="${title}" required>
                                </div>
                                <div class="form-group">
                                    <label for="description">Description</label>
                                    <textarea maxlength="500" id="description" name="description" class="form-control" required>${description}</textarea>
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
                                                <input class="form-check-input" name="categories" type="checkbox" id="inlineCheckbox1" value="For Sale">
                                                <label class="form-check-label" for="inlineCheckbox1">For sale</label>
                                            </div>
                                            <div class="form-check">
                                                <input class="form-check-input" name="categories" type="checkbox" id="inlineCheckbox2" value="Help Wanted">
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
