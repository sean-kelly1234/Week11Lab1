<%-- 
    Document   : resetNewPassword
    Created on : Nov 22, 2017, 12:58:07 PM
    Author     : 642202
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reset Password</title>
    </head>
    <body>
        <h1>Reset Password</h1>
        <p>Please enter your new password</p>
        <form action="reset" method="POST">
            Password: <input type="password" name="password"><br>
            <input type="submit" value="Submit">
            <input type="hidden" name="action" value="resetPassword">
            <input type="hidden" name="uuid" value="${uuid}">
        </form>
    </body>
</html>
