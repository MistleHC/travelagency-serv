<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" />


<html>
<jsp:include page="home_head.jsp">
    <jsp:param name="titleName" value="Management"/>
</jsp:include>
<body>
<div class="container-fluid">

    <jsp:include page="navbar.jsp"/>

    <div class="bg-light">
        <h1 class="text-center"><fmt:message key="profile.orders" /></h1><br>
        <c:choose>
            <c:when test="${empty sessionScope.orders}">
                <h4>There no orders</h4>
            </c:when>
            <c:otherwise>
                <table class="table table-hover table-bordered">
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col"><fmt:message key="man.customer.id" /></th>
                        <th scope="col"><fmt:message key="man.customer.login" /></th>
                        <th scope="col"><fmt:message key="man.tour.name" /></th>
                        <th scope="col"><fmt:message key="profile.o.price" /></th>
                        <th scope="col"><fmt:message key="man.actions" /></th>
                    </tr>
                    <c:forEach items="${sessionScope.orders}" var="order">
                        <tr>
                            <td>
                                    ${order.id}
                            </td>
                            <td>
                                    ${order.customer.id}
                            </td>
                            <td>
                                    ${order.customer.name}
                            </td>
                            <td>
                                    ${order.tour.name}
                            </td>
                            <td>
                                    ${order.tour.price - (order.tour.price * order.tour.discount / 100)} UAH
                            </td>
                            <td>
                                <div class="card-body row no-gutters align-items-center">
                                    <div class="col-md-2">
                                        <form action="${pageContext.request.contextPath}/app/manage" method="get">
                                            <input type="hidden" name="paidOrderId" value="${order.id}" />
                                            <button class="btn btn-sm btn-success search-btn" type="submit"><fmt:message key="man.a.paid" /></button>
                                        </form>
                                    </div>
                                    <div class="col-md-2">
                                        <form action="${pageContext.request.contextPath}/app/manage" method="get">
                                            <input type="hidden" name="declineOrderId" value="${order.id}" />
                                            <button class="btn btn-sm btn-warning search-btn" type="submit"><fmt:message key="man.a.decline" /></button>
                                        </form>
                                    </div>
                                    <div class="col-md-2">
                                        <form action="${pageContext.request.contextPath}/app/manage" method="get">
                                            <input type="hidden" name="deleteOrderId" value="${order.id}" />
                                            <button class="btn btn-sm btn-danger search-btn" type="submit"><fmt:message key="man.a.delete" /></button>
                                        </form>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
    </div>

</div>
</body>
</html>