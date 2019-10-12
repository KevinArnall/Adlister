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
            <form class="form-inline" method="POST" action="/ads">
                <input name="search" type="text" class="form-control mb-2 mr-sm-2" id="inlineFormInputName2" placeholder="Search">

                <button type="submit" class="btn btn-primary mb-2">Search</button>
            </form>
            <div class="row">
                <jsp:include page="/WEB-INF/partials/partialAd.jsp"/>
            </div>
        </div>
    </body>
</html>
