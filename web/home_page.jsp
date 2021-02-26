<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" />

<html>
    <jsp:include page="${pageContext.request.contextPath}/home_head.jsp">
        <jsp:param name="titleName" value="Tours"/>
    </jsp:include>

<body>
<div class="container-fluid">
    <jsp:include page="${pageContext.request.contextPath}/navbar.jsp"/>


        <div class="row2">
            <form action="" class="card card-sm border-light" method="get">
                <div class="card-body row no-gutters align-items-center">
                    <div class="col-auto">
                        <i class="fas fa-search h4 text-body"></i>
                    </div>
                    <div class="col">
                        <h3 class="h-in"><fmt:message key="search.country" /></h3>
                        <label>
                            <select class="form-select form-select-lg sel-tx" name="country">
                                <option value="all"><fmt:message key="search.all" /></option>
                                <c:forEach items="${sessionScope.countries}" var="country">
                                    <option value="${country.name}">${country.name}</option>
                                </c:forEach>
                            </select>
                        </label>
                        <h3 class="h-in"><fmt:message key="search.hotel" /></h3>
                        <label>
                            <select class="form-select form-select-lg sel-tx" name="hotel">
                                <option value="all"><fmt:message key="search.all" /></option>
                                <c:forEach items="${sessionScope.hotels}" var="hotel">
                                    <option value="${hotel.name}">${hotel.name}</option>
                                </c:forEach>
                            </select>
                        </label>
                        <h3 class="h-in"><fmt:message key="search.price" /> </h3>
                        <label>
                            <input type="text" class="form-control" name="lowerprice" placeholder="<fmt:message key="search.money.more" />" pattern="[0-9]{0,10}">
                        </label>
                        <label>
                            <input type="text" class="form-control" name="higherprice" placeholder="<fmt:message key="search.money.less" />" pattern="[0-9]{0,10}">
                        </label>
                        <h3 class="h-in"><fmt:message key="search.groupsize" /></h3>
                        <label>
                            <input type="text" class="form-control" name="lowergroup" placeholder="<fmt:message key="search.group.less" />" pattern="[0-9]{0,2}">
                        </label>
                    </div>
                    <div class="col-auto">
                        <button class="btn btn-lg btn-success search-btn" type="submit"><fmt:message key="search.search" /></button>
                    </div>
                    <c:if test="${sessionScope.authUser.role == 'ADMIN'}">
                        <div class="col-auto">
                            <a href="#" data-toggle="modal" data-target="#modalcreate" class="btn btn-lg btn-outline btn-primary"><fmt:message key="search.create" /><i class="fa fa-long-arrow-right"></i> </a>
                        </div>
                    </c:if>
                </div>
            </form>
        </div>

    <!-- Modal - Tour creation -->
    <div class="modal fade" id="modalcreate">
        <div class="modal-dialog modal-dialog-centered">
            <form action="${pageContext.request.contextPath}/app/tour/create" method="post">
            <div class="modal-content">
                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title"><fmt:message key="create.title" /></h4> <button type="button" class="close cl-btn" data-dismiss="modal">&times;</button>
                </div> <!-- Modal body -->
                <div class="modal-body">
                    <div>
                        <div>
                            <div class="product-desc">
                                <label for="tourName" class="form-label"><fmt:message key="create.name" /></label>
                                <input name="tourName" type="text" class="form-control" id="tourName" placeholder="Name"/>
                                <label for="tourDescription" class="form-label"><fmt:message key="create.description" /></label>
                                <textarea rows = "4" cols = "60" name = "tourDescription" id="tourDescription">
                                </textarea><br>
                                <label class="form-label"><fmt:message key="create.type" /></label>
                                <label>
                                    <select class="form-select form-select-lg sel-tx" name="tourType">
                                        <c:forEach items="${sessionScope.tourTypes}" var="ttype">
                                            <option value="${ttype.name}">${ttype.name}</option>
                                        </c:forEach>
                                    </select>
                                </label> <br>
                                <label class="form-label"><fmt:message key="create.country" /></label>
                                <label>
                                    <select class="form-select form-select-lg sel-tx" name="tourCountry">
                                        <c:forEach items="${sessionScope.countries}" var="country">
                                            <option value="${country.name}">${country.name}</option>
                                        </c:forEach>
                                    </select>
                                </label> <br>
                                <label for="tourSize" class="form-label"><fmt:message key="create.group" /></label>
                                <input name="tourSize" type="text" class="form-control" id="tourSize" placeholder="0" pattern="[0-9]{0,2}"/>
                                <label class="form-label"><fmt:message key="create.hotel" /></label>
                                <label>
                                    <select class="form-select form-select-lg sel-tx" name="tourHotel">
                                        <c:forEach items="${sessionScope.hotels}" var="hotel">
                                            <option value="${hotel.name}">${hotel.name}</option>
                                        </c:forEach>
                                    </select>
                                </label> <br>
                                <label for="tourPrice" class="form-label"><fmt:message key="create.price" /> </label>
                                <input name="tourPrice" type="text" class="form-control" id="tourPrice" placeholder="UAH" pattern="[0-9]{0,10}"/>
                            </div>
                        </div>
                    </div>
                </div> <!-- Modal footer -->
                <c:if test="${sessionScope.authUser != null}">
                    <div class="modal-footer">
                        <c:if test="${sessionScope.authUser.role == 'ADMIN'}">
                            <button class="btn btn-lg btn-success search-btn" type="submit"><fmt:message key="search.create" /></button>
                        </c:if>
                    </div>
                </c:if>
            </div>
            </form>
        </div>
    </div>

    <div class="row">

        <c:choose>
            <c:when test="${empty sessionScope.tours}">
                <h4>No tours available right now :(</h4>
            </c:when>
            <c:otherwise>
                <c:forEach items="${sessionScope.tours}" var="tour">

                    <!-- Modal -->
                    <div class="modal fade" id="modal${tour.id}">
                        <div class="modal-dialog modal-dialog-centered">
                            <div class="modal-content">
                                <!-- Modal Header -->
                                <div class="modal-header">
                                    <h4 class="modal-title">${tour.name}</h4> <button type="button" class="close cl-btn" data-dismiss="modal">&times;</button>
                                </div> <!-- Modal body -->
                                <div class="modal-body">
                                    <div>
                                        <div>
                                            <div class="product-imitation">
                                                <script type="text/javascript">
                                                    document.write(getImageTag());
                                                </script>
                                            </div>
                                            <div class="product-desc">
                                                <span class="product-price">
                                                        ${tour.price - (tour.price * tour.discount / 100)} UAH
                                                </span>
                                                <h4 class="pro-name">${tour.name}</h4><br>
                                                <p class="prod-name"><fmt:message key="create.type" /> ${tour.tourType.name}</p>
                                                <p class="prod-name"><fmt:message key="create.hotel" /> ${tour.hotelType.name}</p>
                                                <p class="prod-name"><fmt:message key="create.group" /> ${tour.peoples}</p><br>
                                                <p class="prod-name">${tour.description}</p><br>
                                                <p class="prod-name"></p>
                                                <p class="prod-name"><fmt:message key="create.price" /> ${tour.price - (tour.price * tour.discount / 100)}</p>
                                            </div>
                                        </div>
                                    </div>
                                </div> <!-- Modal footer -->
                                <c:if test="${sessionScope.authUser != null}">
                                <div class="modal-footer">
                                    <c:if test="${sessionScope.authUser.role == 'CUSTOMER'}">
                                        <form action="${pageContext.request.contextPath}/app/order" method="get">
                                            <input type="hidden" name="tourid" value="${tour.id}" />
                                            <button class="btn btn-lg btn-success search-btn" type="submit"><fmt:message key="item.buy" /></button>
                                        </form>
                                    </c:if>
                                    <c:choose>
                                        <c:when test="${sessionScope.authUser.role == 'MANAGER'}">
                                            <form action="${pageContext.request.contextPath}/app/tour/hot" method="get">
                                                <input type="hidden" name="hottourid" value="${tour.id}" />
                                                <button class="btn btn-lg btn-warning search-btn" type="submit"><fmt:message key="item.hot" /></button>
                                            </form>
                                            <form action="${pageContext.request.contextPath}/app/tour/de-hot" method="get">
                                                <input type="hidden" name="dehottourid" value="${tour.id}" />
                                                <button class="btn btn-lg btn-warning search-btn" type="submit"><fmt:message key="item.nothot" /></button>
                                            </form>
                                        </c:when>
                                        <c:when test="${sessionScope.authUser.role == 'ADMIN'}">
                                            <form action="${pageContext.request.contextPath}/app/tour/hot" method="get">
                                                <input type="hidden" name="hottourid" value="${tour.id}" />
                                                <button class="btn btn-lg btn-warning search-btn" type="submit"><fmt:message key="item.hot" /></button>
                                            </form>
                                            <form action="${pageContext.request.contextPath}/app/tour/de-hot" method="get">
                                                <input type="hidden" name="dehottourid" value="${tour.id}" />
                                                <button class="btn btn-lg btn-warning search-btn" type="submit"><fmt:message key="item.nothot" /></button>
                                            </form>
                                        </c:when>
                                    </c:choose>
                                    <c:if test="${sessionScope.authUser.role == 'ADMIN'}">
                                        <form action="${pageContext.request.contextPath}/app/tour/delete" method="get">
                                            <input type="hidden" name="deltourid" value="${tour.id}" />
                                            <button class="btn btn-lg btn-danger search-btn" type="submit"><fmt:message key="item.delete" /></button>
                                        </form>
                                    </c:if>
                                </div>
                                </c:if>
                            </div>
                        </div>
                    </div>

                <div class="col-md-3 item-row">
                    <div class="ibox">
                        <div class="ibox-content product-box">
                            <div data-toggle="modal" data-target="#modal${tour.id}" class="product-imitation" id="imgcont">
                                <script type="text/javascript">
                                    document.write(getImageTag());
                                </script>
                            </div>
                            <div class="product-desc">
                    <span class="product-price">
                            ${tour.price - (tour.price * tour.discount / 100)} UAH
                    </span>
                                <small class="text-muted">${tour.tourType.name}</small>
                                <a href="#" data-toggle="modal" data-target="#modal${tour.id}" class="product-name">${tour.name}</a>

                                <div class="small m-t-xs">
                                    <fmt:message key="create.group" /> ${tour.peoples}
                                </div>
                                <div class="small m-t-xs">
                                        ${tour.description}
                                </div>
                                <div class="m-t text-righ item-btn">
                                    <a href="#" data-toggle="modal" data-target="#modal${tour.id}" class="btn btn-sm btn-outline btn-primary"><fmt:message key="item.info" /><i class="fa fa-long-arrow-right"></i> </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div>

    <nav aria-label="Navigation for tours">
        <ul class="pagination paging-element">
            <c:if test="${sessionScope.currentPage != 1}">
                <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/app/home?page=${sessionScope.currentPage - 1}"><fmt:message key="page.prev" /></a></li>
            </c:if>

            <c:forEach begin="1" end="${sessionScope.noOfPages}" var="i">
                <c:choose>
                    <c:when test="${sessionScope.currentPage eq i}">
                        <li class="page-item active"><a class="page-link">
                                ${i} <span class="sr-only">(current)</span></a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/app/home?page=${i}">${i}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:if test="${sessionScope.currentPage lt sessionScope.noOfPages}">
                <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/app/home?page=${sessionScope.currentPage+1}"><fmt:message key="page.next" /></a>
                </li>
            </c:if>
        </ul>
    </nav>

</div>

</body>
</html>