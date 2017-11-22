<%-- 
    Document   : forgot
    Created on : Nov 20, 2017, 8:33:51 AM
    Author     : 642202
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forgot Password</title>
    </head>
    <body>
        <h1>Forgot Password</h1>
        <p>Please enter your email address to reset your password</p>
        <form action="reset" method="POST">
            Email Address: <input type="text" name="email"><br>
            <input type="submit" value="Submit">
            <input type="hidden" name="action" value="sendEmail">
        </form>
    </body>
</html>
