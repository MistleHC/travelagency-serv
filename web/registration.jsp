<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <jsp:include page="home_head.jsp">
        <jsp:param name="titleName" value="Register"/>
    </jsp:include>
<body>
<div class="container-fluid">
    <jsp:include page="navbar.jsp"/>

    <div class="row check-b">
        <h1 class="text-center">Registration form</h1>
    </div>

    <div class="row check-b">
        <form action="/app/registration" method="post">

            <div class="mb-3">
                <label for="userName" class="form-label">Name</label>
                <input name="name" type="text" class="form-control" id="userName" placeholder="Enter name" pattern="{4,16}"/>
            </div>

            <div class="mb-3">
                <label for="userEmail" class="form-label">Email address</label>
                <input name="email" type="email" class="form-control" id="userEmail" placeholder="name@example.com"/>
            </div>

            <div class="mb-3">
                <label for="userPassword" class="form-label">Password</label>
                <input name="password" type="password" class="form-control" id="userPassword" placeholder="Your password" pattern="{4,22}"/>
            </div>

            <div class="mb-3">
                <button class="btn btn-success" type="submit">Register</button>
            </div>

            <div class="invalid-feedback d-block">
                <i>${sessionScope.error}</i>
            </div>

        </form>
    </div>

</div>
</body>
</html>
