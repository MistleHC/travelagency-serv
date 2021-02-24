<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" />

<script>
    function goto(form) {
        var index = form.select.selectedIndex;
        if (form.select.options[index].value != "0") {
            location = form.select.options[index].value;
        }
    }
</script>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">Travel Agency</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="<c:url value="${pageContext.request.contextPath}/app/home"/>"><fmt:message key="nav.home" /></a>
            </li>
            <c:if test="${sessionScope.authUser == null}">
                <li class="nav-item active">
                    <a class="nav-link" href="<c:url value="${pageContext.request.contextPath}/app/registration"/>"><fmt:message key="nav.register" /></a>
                </li>
            </c:if>
            <c:if test="${sessionScope.authUser == null}">
                <li class="nav-item active">
                    <a class="nav-link" href="<c:url value="${pageContext.request.contextPath}/app/login"/>"><fmt:message key="nav.login" /></a>
                </li>
            </c:if>
            <c:if test="${sessionScope.authUser != null}">
                <c:choose>
                    <c:when test="${sessionScope.authUser.role == 'MANAGER'}">
                        <li class="nav-item active">
                            <a class="nav-link" href="<c:url value="${pageContext.request.contextPath}/app/manage"/>"><fmt:message key="nav.management" /></a>
                        </li>
                    </c:when>
                    <c:when test="${sessionScope.authUser.role == 'ADMIN'}">
                        <li class="nav-item active">
                            <a class="nav-link" href="<c:url value="${pageContext.request.contextPath}/app/manage"/>"><fmt:message key="nav.management" /></a>
                        </li>
                    </c:when>
                </c:choose>
            </c:if>
            <c:if test="${sessionScope.authUser != null}">
                <li class="nav-item active">
                    <a class="nav-link" href="<c:url value="${pageContext.request.contextPath}/app/profile"/>"><fmt:message key="nav.profile" /></a>
                </li>
            </c:if>
            <c:if test="${sessionScope.authUser != null}">
                <li class="nav-item active">
                    <a class="nav-link" href="<c:url value="${pageContext.request.contextPath}/app/logout"/>"><fmt:message key="nav.logout" /></a>
                </li>
            </c:if>
        </ul>
    </div>

    <form name="form1" class="card card-sm border-light nav-link inf-btn">
        <label>
            <select name="select" onchange="goto(this.form)" size="1" class="form-select form-select-sm sel-tx">
                <option value="">Lang...</option>
                <option value="${pageContext.request.contextPath}/?lang=en-US">ENG</option>
                <option value="${pageContext.request.contextPath}/?lang=ru">RU</option>
            </select>
        </label>
    </form>
</nav>
