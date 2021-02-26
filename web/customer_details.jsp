<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" />


<html>
<jsp:include page="home_head.jsp">
    <jsp:param name="titleName" value="Customer details"/>
</jsp:include>
<body>
<div class="container-fluid">

    <jsp:include page="navbar.jsp"/>

    <div class="bg-light">
        <h1 class="text-center hd-t"><fmt:message key="profile.title" /></h1><br>
        <div class="row-p col" id="block-p1">
            <h4 class="text-left"><fmt:message key="profile.login" /> ${sessionScope.authUser.name}</h4>
            <h4 class="text-left"><fmt:message key="profile.name" /> ${sessionScope.authUser.fullName}</h4>
            <h4 class="text-left"><fmt:message key="profile.email" /> ${sessionScope.authUser.email}</h4>
            <h4 class="text-left"><fmt:message key="profile.about" /> ${sessionScope.authUser.aboutMe}</h4>
        </div>
        <div class="col" id="block-p2">
            <a href="#" data-toggle="modal" data-target="#modaledit" class="btn btn-lg btn-outline btn-primary"><fmt:message key="profile.edit" /><i class="fa fa-long-arrow-right"></i> </a>
        </div>
    </div>

    <div class="bg-light">
        <h1 class="text-center"><fmt:message key="profile.orders" /></h1><br>
        <c:choose>
            <c:when test="${empty sessionScope.orders}">
                <h4>User has no orders</h4>
            </c:when>
            <c:otherwise>
                <table class="table table-hover table-bordered">
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col"><fmt:message key="profile.o.name" /></th>
                        <th scope="col"><fmt:message key="profile.o.type" /></th>
                        <th scope="col"><fmt:message key="profile.o.hotel" /></th>
                        <th scope="col"><fmt:message key="profile.o.price" /></th>
                        <th scope="col"><fmt:message key="profile.o.status" /></th>
                        <th scope="col"><fmt:message key="profile.o.actions" /></th>
                    </tr>
                    <c:forEach items="${sessionScope.orders}" var="order">
                        <tr>
                            <td>
                                    ${order.id}
                            </td>
                            <td>
                                    ${order.tour.name}
                            </td>
                            <td>
                                    ${order.tour.tourType.name}
                            </td>
                            <td>
                                    ${order.tour.hotelType.name}
                            </td>
                            <td>
                                    ${order.tour.price - (order.tour.price * order.tour.discount / 100)} UAH
                            </td>
                            <td>
                                    ${order.status.title}
                            </td>
                            <td>
                                <c:if test = "${order.status.id == 1}">
                                    <form action="${pageContext.request.contextPath}/app/order/delete" method="get">
                                        <input type="hidden" name="deleteOrderId" value="${order.id}" />
                                        <button class="btn btn-sm btn-success search-btn" type="submit"><fmt:message key="profile.o.cancel" /></button>
                                    </form>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
    </div>

    <!-- Modal - Tour creation -->
    <div class="modal fade" id="modaledit">
        <div class="modal-dialog modal-dialog-centered">
            <form action="${pageContext.request.contextPath}/app/profile" method="post">
                <div class="modal-content">
                    <!-- Modal Header -->
                    <div class="modal-header">
                        <h4 class="modal-title"><fmt:message key="profile.e.edit" /></h4> <button type="button" class="close cl-btn" data-dismiss="modal">&times;</button>
                    </div> <!-- Modal body -->
                    <div class="modal-body">
                        <div>
                            <div>
                                <div class="product-desc">
                                    <label for="userFullName" class="form-label"><fmt:message key="profile.name" /></label>
                                    <input name="userFullName" type="text" class="form-control" id="userFullName" placeholder="Name"/>
                                    <label for="userDescription" class="form-label"><fmt:message key="profile.about" /></label>
                                    <textarea rows = "4" cols = "60" name = "userDescription" id="userDescription"></textarea><br>
                                </div>
                            </div>
                        </div>
                    </div> <!-- Modal footer -->

                    <div class="modal-footer">
                        <button class="btn btn-lg btn-success search-btn" type="submit"><fmt:message key="profile.edit" /></button>
                    </div>
                </div>
            </form>
        </div>
    </div>

</div>
</body>
</html>