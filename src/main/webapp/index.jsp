<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Prog Academy</title>
</head>
<body>
<% String login = (String) session.getAttribute("user_login"); %>
<% Boolean isPassword = (Boolean) session.getAttribute("password"); %>
<% Boolean isLogin = (Boolean) session.getAttribute("my_login"); %>
<% Boolean isAge = (Boolean) session.getAttribute("age"); %>


<% if (login == null || "".equals(login)) { %>

<form action="/login" method="POST">
    Login: <input type="text" name="login"><br>

<%--    <div>Login <%=isLogin%></div>--%>

    <% if(isLogin != null && (!isLogin)) {%>
    <% String message = (String) session.getAttribute("message_login"); %>
    <div style="color: red"><%=message%></div>
    <% } %>

    Age: <input type="text" name="age"><br>
    <% if (isAge != null && !isAge) {%>
    <% String message = (String) session.getAttribute("message_age"); %>
    <div style="color: red"><%=message%></div>
    <% } %>


    Password: <input type="password" name="password"><br>
    <input type="submit"/>
</form>
<% } else { %>

<h1>You are logged in as: <%= login %>
</h1>
<div>Login <%=isLogin%></div>
<div>Age <%=isAge%></div>
Click this link to <a href="/login?a=exit">logout</a>
<% } %>
</body>
</html>
