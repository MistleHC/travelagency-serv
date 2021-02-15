<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<jsp:include page="${pageContext.request.contextPath}/home_head.jsp">
  <jsp:param name="titleName" value="Login"/>
</jsp:include>

  <body>
  <jsp:include page="${pageContext.request.contextPath}/navbar.jsp"/>

  HOME

  <c:redirect url="${pageContext.request.contextPath}/app/home"/>
  </body>
</html>
