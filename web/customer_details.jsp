<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
<jsp:include page="home_head.jsp">
    <jsp:param name="titleName" value="Customer details"/>
</jsp:include>
<body>
<div class="container-fluid">

    <jsp:include page="navbar.jsp"/>

    <div class="bg-light">
        <h1 class="text-center hd-t">Profile</h1><br>
        <div class="row-p col" id="block-p1">
            <h4 class="text-left">Login: ${sessionScope.authUser.name}</h4>
            <h4 class="text-left">Full name: ${sessionScope.authUser.fullName}</h4>
            <h4 class="text-left">Email: ${sessionScope.authUser.email}</h4>
            <h4 class="text-left">About me: ${sessionScope.authUser.aboutMe}</h4>
        </div>
        <div class="col" id="block-p2">
            <a href="#" data-toggle="modal" data-target="#modaledit" class="btn btn-lg btn-outline btn-primary">Edit<i class="fa fa-long-arrow-right"></i> </a>
        </div>
    </div>

    <div class="bg-light">
        <h1 class="text-center">Orders</h1><br>
        <c:choose>
            <c:when test="${empty sessionScope.orders}">
                <h4>User has no orders</h4>
            </c:when>
            <c:otherwise>
                <table class="table table-hover table-bordered">
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Tour name</th>
                        <th scope="col">Tour type</th>
                        <th scope="col">Hotel type</th>
                        <th scope="col">Status</th>
                        <th scope="col">Actions</th>
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
                                    ${order.status.title}
                            </td>
                            <td>
                                <c:if test = "${order.status.id == 1}">
                                    <form action="${pageContext.request.contextPath}/app/order/delete" method="get">
                                        <input type="hidden" name="deleteOrderId" value="${order.id}" />
                                        <button class="btn btn-sm btn-success search-btn" type="submit">Cancel</button>
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
                        <h4 class="modal-title">Edit profile</h4> <button type="button" class="close cl-btn" data-dismiss="modal">&times;</button>
                    </div> <!-- Modal body -->
                    <div class="modal-body">
                        <div>
                            <div>
                                <div class="product-desc">
                                    <label for="userFullName" class="form-label">Full name:</label>
                                    <input name="userFullName" type="text" class="form-control" id="userFullName" placeholder="Name"/>
                                    <label for="userDescription" class="form-label">About me:</label>
                                    <textarea rows = "4" cols = "60" name = "userDescription" id="userDescription"></textarea><br>
                                </div>
                            </div>
                        </div>
                    </div> <!-- Modal footer -->

                    <div class="modal-footer">
                        <button class="btn btn-lg btn-success search-btn" type="submit">Edit</button>
                    </div>
                </div>
            </form>
        </div>
    </div>

</div>
</body>
</html>