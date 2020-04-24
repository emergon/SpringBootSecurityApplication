<%-- 
    Document   : register
    Created on : Apr 24, 2020, 8:08:59 PM
    Author     : user
--%>
<!DOCTYPE HTML >
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Register Page</title>
    </head>
    <body>
        <div align="center">
            <h3>Registration Form</h3>
            <form:form action="${pageContext.request.contextPath}/admin/register" 
                       method="post" modelAttribute="myuser">
                <p>
                    <label>Enter username</label>  
                    <form:input path="username"/>
                </p>
                <p>
                    <label>Enter password</label> 
                    <form:input path="password"/>
                </p>
                <p>
                    <label>Choose roles</label>
                    <form:select multiple="true" path="roles">
                        <form:options items="${roloi}"/>
                    </form:select>
                </p>
                <input type="submit" value="Register">
            </form:form>
        </div>
    </body>
</html>

