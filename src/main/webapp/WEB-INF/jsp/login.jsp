<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Содержимое базы</title>
    <link href="<c:url value='/main.css'/>" rel="stylesheet" type="text/css">
</head>
<body>
You are now logged as ${user} <br/>
<a href="admin.do"/>Администрирование</a>
        <form action="check.do" method="post">  <input type="submit" value="Войти под пользователем: "/> <input name="user"/> </form>
        <div class="status">${error}</div>

</body>
</html>
