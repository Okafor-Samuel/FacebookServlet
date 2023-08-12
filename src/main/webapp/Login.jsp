<%--
Created by IntelliJ IDEA.
User: mabookpro
Date: 7/25/23
Time: 7:00 AM
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="Style.css">
    <title>Log in</title>
</head>
<body>
<div class="container login">
    <div class="form-box">
        <div class="logo-box">
            <img src="${pageContext.request.contextPath}/Images/facebook.png" alt="Facebook logo" class="logo" />
        </div>

        <form action="GET" action="/login"> /dashboard">
            <p class="form-heading">Log in to Facebook<p/>
            <input type="text" placeholder="Email address or phone number"/>
            <input type="password" placeholder="Password"/>
            <button type="submit" class="login-btn">Log In</button>
            <a href="#" class="forgotten-acc">Forgotten account?</a>
            <div class="new-acc">or</div>
            <button class="new-btn" type="button">
                <a href="./index.jsp">Create New Account</a>
            </button>
        </form>
    </div>
</div>
</body>
</html>
