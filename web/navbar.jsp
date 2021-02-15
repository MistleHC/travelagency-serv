<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">Travel Agency</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="<c:url value="${pageContext.request.contextPath}/app/home"/>">Home</a>
            </li>
            <c:if test="${sessionScope.authUser == null}">
                <li class="nav-item active">
                    <a class="nav-link" href="<c:url value="${pageContext.request.contextPath}/app/registration"/>">Register</a>
                </li>
            </c:if>
            <c:if test="${sessionScope.authUser == null}">
                <li class="nav-item active">
                    <a class="nav-link" href="<c:url value="${pageContext.request.contextPath}/app/login"/>">Login</a>
                </li>
            </c:if>
            <c:if test="${sessionScope.authUser != null}">
                <c:choose>
                    <c:when test="${sessionScope.authUser.role == 'MANAGER'}">
                        <li class="nav-item active">
                            <a class="nav-link" href="<c:url value="${pageContext.request.contextPath}/app/manage"/>">Management</a>
                        </li>
                    </c:when>
                    <c:when test="${sessionScope.authUser.role == 'ADMIN'}">
                        <li class="nav-item active">
                            <a class="nav-link" href="<c:url value="${pageContext.request.contextPath}/app/manage"/>">Management</a>
                        </li>
                    </c:when>
                </c:choose>
            </c:if>
            <c:if test="${sessionScope.authUser != null}">
                <li class="nav-item active">
                    <a class="nav-link" href="<c:url value="${pageContext.request.contextPath}/app/profile"/>">Profile</a>
                </li>
            </c:if>
            <c:if test="${sessionScope.authUser != null}">
                <li class="nav-item active">
                    <a class="nav-link" href="<c:url value="${pageContext.request.contextPath}/app/logout"/>">Logout</a>
                </li>
            </c:if>
        </ul>
    </div>
</nav>
