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
                                    <input id="username" name="username" class="form-control" type="text">
                                </div>
                                <div class="form-group">
                                    <label for="password">Password</label>
                                    <input id="password" name="password" class="form-control" type="password">
                                </div>
                                <input type="submit" class="btn btn-primary btn-block" value="Log In">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
