<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <jsp:include page="/WEB-INF/partials/head.jsp">
            <jsp:param name="title" value="Please Log In"/>
        </jsp:include>
    </head>
    <body>
        <jsp:include page="/WEB-INF/partials/navbar.jsp"/>
        <div class="container">
            <div class="row align-items-center justify-content-center vh-100">
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-body text-center">
                            <h1 class="card-title">Log In</h1>
                            <form action="/login" method="POST">
                                <div class="form-group">
                                    <label for="username">Username</label>
                                    <c:choose>
                                        <c:when test="${usernotfound}">
                                            <input id="username" name="username" class="form-control is-invalid" type="text">
                                            <div class="invalid-feedback">
                                                User not found
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <input id="username" name="username" class="form-control" type="text">
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div class="form-group">
                                    <label for="password">Password</label>
                                    <c:choose>
                                        <c:when test="${invalidpassword}">
                                            <input id="password" name="password" class="form-control is-invalid" type="password">
                                            <div class="invalid-feedback">
                                                Invalid password
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <input id="password" name="password" class="form-control" type="password">
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <button class="btn btn-primary btn-block" type="submit">Login</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
