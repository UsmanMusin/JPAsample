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
<a class="adminButton" href="admin.do"/>Закрыть</a><br>
<form action="updateemp${employee.id}.do" method='post'>

    <table>

        <tr>
            <td><b>Имя</b></td>
            <td><input type='text' name='name' class='form-control' size="20" value="${employee.name}"/></td>
        </tr>


        <tr>
            <td><b>Фамилия</b></td>
            <td><input type='text' name='surname' class='form-control' size="20" value="${employee.surname}" /></td>
        </tr>

        <tr>
            <td><b>Отчество</b></td>
            <td><input type='text' name='middleName' class='form-control' size="20" value="${employee.middleName}" /></td>
        </tr>

        <tr>
            <td><b>Специальность</b></td>
            <td><input type='text' name='position' class='form-control' size="20" value="${employee.position}" /></td>
        </tr>

        <tr>
            <td><b>Пароль</b></td>
            <td><input type='text' name='password' class='form-control' size="20" value="${employee.password}" /></td>
        </tr>



        <tr>
            <td></td>
            <td>
                <button type="submit" class="adminButton">Сохранить</button>
            </td>
        </tr>

    </table>
</form>
</body>
</html>
