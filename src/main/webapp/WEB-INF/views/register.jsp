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
            <c:if test="${userExistsError != null}">
                ${userExistsError}
            </c:if>
            <form:form action="${pageContext.request.contextPath}/admin/register" 
                       method="post" modelAttribute="myuser">
                
                <p>
                    <form:input path="username" placeholder="username"/>
                </p>
                <p>
                    <form:input path="password" placeholder="password"/>
                </p>
                <p>
                    <form:input path="fname" placeholder="first name"/>
                </p>
                <p>
                    <form:input path="lname" placeholder="last name"/>
                </p>
                <p>
                    <form:input path="email" placeholder="email"/>
                </p>
                <p>
                    <label>Choose roles</label>
                    <form:select multiple="true" path="roles">
                        <form:options items="${roloi}" itemLabel="rname" itemValue="rid"/>
                    </form:select>
                </p>
                <input type="submit" value="Register">
            </form:form>
        </div>
    </body>
</html>

