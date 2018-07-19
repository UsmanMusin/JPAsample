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

<form action="addemp.do" method='post'>

    <table>

        <tr>
            <td><b>Имя</b></td>
            <td><input type='text' name='name' class='form-control' size="20"/></td>
        </tr>


        <tr>
            <td><b>Фамилия</b></td>
            <td><input type='text' name='surname' class='form-control' size="20" /></td>
        </tr>

        <tr>
            <td><b>Отчество</b></td>
            <td><input type='text' name='middlename' class='form-control' size="20" /></td>
        </tr>

        <tr>
            <td><b>Специальность</b></td>
            <td><input type='text' name='position' class='form-control' size="20" /></td>
        </tr>

        <tr>
            <td><b>Пароль</b></td>
            <td><input type='text' name='password' class='form-control' size="20" /></td>
        </tr>



        <tr>
            <td></td>
            <td>
                <button type="submit" class="adminButton">Создать</button>
            </td>
        </tr>

    </table>
</form>
</body>
</html>
