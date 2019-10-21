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
                                    <input id="title" name="title" class="form-control" type="text" required>
                                </div>
                                <div class="form-group">
                                    <label for="description">Description</label>
                                    <textarea id="description" name="description" class="form-control" required></textarea>
                                </div>
                                <div class="form-group">
                                    <label>Categories</label>
                                    <div class="form-check">
                                        <input class="form-check-input" name="categories" type="checkbox" id="inlineCheckbox1" value="For Sale">
                                        <label class="form-check-label" for="inlineCheckbox1">For sale</label>
                                    </div>
                                    <div class="form-check">
                                        <input class="form-check-input" name="categories" type="checkbox" id="inlineCheckbox2" value="Help Wanted">
                                        <label class="form-check-label" for="inlineCheckbox2">Help Wanted</label>
                                    </div>
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
