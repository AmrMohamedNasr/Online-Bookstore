<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<a href="${pageContext.request.contextPath}/home">
  Home
</a>
||
<c:choose>
<c:when test="${empty loginedUser}">
       <a href="${pageContext.request.contextPath}/login">
  			Login
		</a>
		||
		<a href="${pageContext.request.contextPath}/register">
  			Register
		</a>
    </c:when>
    <c:otherwise>
    <c:choose>
    	<c:when test="${loginedUser.manager}">
	    	<a href="${pageContext.request.contextPath}/manager">
			  Manager
			</a>
			||
		</c:when>
		<c:otherwise>
		</c:otherwise>
		</c:choose>
		<a href="${pageContext.request.contextPath}/user">
		  User
		</a>       
		||
        <a href="${pageContext.request.contextPath}/logout">
  			Logout
		</a>
    </c:otherwise>
</c:choose>