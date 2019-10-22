<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <jsp:include page="partials/head.jsp">
            <jsp:param name="title" value="Register For Our Site!"/>
        </jsp:include>
    </head>
    <body>
        <jsp:include page="partials/navbar.jsp"/>
        <div class="container">
            <div class="row align-items-center justify-content-center vh-100">
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-body text-center">
                            <h1 class="card-title">Register</h1>
                            <form action="/register" method="post">
                                <div class="form-group">
                                    <label for="username">Username</label>
                                    <c:choose>
                                        <c:when test="${usernamelength.length() > 0}">
                                            <input id="username" name="username" class="form-control is-invalid" type="text">
                                            <div class="invalid-feedback">
                                                    ${usernamelength}
                                            </div>
                                        </c:when>
                                        <c:when test="${usernameexists.length() > 0}">
                                            <input id="username" name="username" class="form-control is-invalid" type="text">
                                            <div class="invalid-feedback">
                                                    ${usernameexists}
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <input id="username" name="username" class="form-control" type="text">
                                            <small class="form-text text-muted">Usernames should be at least 4 letters long</small>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div class="form-group">
                                    <label for="email">Email</label>
                                    <c:choose>
                                        <c:when test="${email.length() > 0}">
                                            <input id="email" name="email" class="form-control is-invalid" type="text">
                                            <div class="invalid-feedback">
                                                    ${email}
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <input id="email" name="email" class="form-control" type="text">
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div class="form-group">
                                    <label for="password">Password</label>
                                    <c:choose>
                                        <c:when test="${passwordlength.length() > 0}">
                                            <input id="password" name="password" class="form-control is-invalid" type="password">
                                            <div class="invalid-feedback">
                                                    ${passwordlength}
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <input id="password" name="password" class="form-control" type="password">
                                            <small class="form-text text-muted">Passwords should be at least 8 letters long</small>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div class="form-group">
                                    <label for="confirm_password">Confirm Password</label>
                                    <c:choose>
                                        <c:when test="${passwordmatch.length() > 0}">
                                            <input id="confirm_password" name="confirm_password" class="form-control is-invalid" type="password">
                                            <div class="invalid-feedback">
                                                    ${passwordmatch}
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <input id="confirm_password" name="confirm_password" class="form-control" type="password">
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <button class="btn btn-primary btn-block" type="submit">Submit</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
