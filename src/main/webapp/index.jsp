<%@ page import="src.main.service.UserServiceHebernate" %>
<%
    UserServiceHebernate userServiceHebernate = UserServiceHebernate.getInstance();
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>Проект 2 предпроектной подготовки</h2>
<hr/>
<p> Добавить User-a в БД
    <%--    <a href="registration.html">Добавить пользователя</a>--%>
    <form action="${pageContext.request.contextPath}/registration" method="POST">
        Name: <input type="text" name="name"/>
<p>
    Password: <input type="password" name="password"/>
<p>
    <input type="submit" value="Добавить">
    </form>
<hr/>
<form action="${pageContext.request.contextPath}/delete" method="POST">
    Удалить пользователя по Id
    Id: <input type="text" name="id"/>
    <p>
        <input type="submit" value="Удалить">
</form>
<hr/>
<form action="${pageContext.request.contextPath}/edit" method="POST">
    <p>
        ID: <input type="text" name="id"/>
    <p>
        New Name: <input type="text" name="name"/>
    <p>
        New Password: <input type="text" name="password"/>
    <p>
        <input type="submit" value="Изменить">
</form>
<hr/>
<%--<p> Пользователи <%= userService.getAllUsers().toString() %>--%>
<p> Пользователи <%= userServiceHebernate.getAllUsers().toString() %>

</p>
</body>
</html>