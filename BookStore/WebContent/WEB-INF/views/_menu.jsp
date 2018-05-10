<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class ="navbar">
<ul class = "navbar">
<li><a href="${pageContext.request.contextPath}/home" id ="homeNav">
  Home
</a></li>
<c:choose>
<c:when test="${empty loginedUser}">
       <li><a href="${pageContext.request.contextPath}/login" id = "loginNav">
       		Login
		</a></li>
		<li>
		<a href="${pageContext.request.contextPath}/register" id = "registerNav">
  			Register
		</a>
		</li>
    </c:when>
    <c:otherwise>
    <c:choose>
    	<c:when test="${loginedUser.manager}">
    		<li>
	    	<a href="${pageContext.request.contextPath}/manager" id = "managerNav">
			  Manager
			</a>
			</li>
		</c:when>
		<c:otherwise>
		</c:otherwise>
		</c:choose>
		<li>
		<a href="${pageContext.request.contextPath}/user" id = "userNav">
		  User
		</a>       
		</li>
		<li>
        <a href="${pageContext.request.contextPath}/logout" id = "logoutNav">
  			Logout
		</a>
		</li>
    </c:otherwise>
</c:choose>
</ul></div>