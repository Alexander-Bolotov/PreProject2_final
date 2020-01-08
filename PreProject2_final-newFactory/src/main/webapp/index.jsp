<%@ page import="src.main.service.Service" %>
<%
    Service service = Service.getInstance();
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<body>
<h2>Проект 3 предпроектной подготовки Filter User/Admin</h2>
<hr/>
<form method="POST" action="${pageContext.request.contextPath}/filter">
    <table>
        <tr>
            <td><label for="loginField">Логин</label></td>
            <td><input id="loginField" type="text" name="name"></td>
        </tr>
        <tr>
            <td><label for="passwordField">Пароль</label></td>
            <td><input id="passwordField" type="password" name="password"></td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center"><input type="submit" value="Войти"></td>
        </tr>
    </table>
</form>
</body>
</html>