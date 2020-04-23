<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home App</title>
    </head>
    <body>
        <h3>Welcome to Spring Security</h3>
        
        <hr/>
        <p>
            User: <security:authentication property="principal.username"/>
            <br/>
            Role(s):<security:authentication property="principal.authorities"/>
        </p>
        <hr/>
        <a href="${pageContext.request.contextPath}/admin/home">Go to Admin Home page</a>
        
        <form:form action="${pageContext.request.contextPath}/logout" method="POST">
            <input type="submit" value="Logout"/>
        </form:form>
    </body>
</html>