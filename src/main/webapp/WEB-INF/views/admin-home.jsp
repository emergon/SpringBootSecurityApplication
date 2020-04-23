<%-- 
    Document   : admin-home
    Created on : Apr 23, 2020, 9:25:29 PM
    Author     : user
--%>
<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Admin Page</title>
    </head>
    <body>
        <hr/>
        <p>
            User: <security:authentication property="principal.username"/>
            <br/>
            Role(s):<security:authentication property="principal.authorities"/>
        </p>
        <hr/>
        <h1>Welcome Admin. You can do anything!!!</h1>

    </body>
</html>

