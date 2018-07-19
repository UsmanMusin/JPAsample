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
<a class="adminButton" href="userpage.do"/>Отмена</a><br>
<div id = "demoFont">Новое поручение</div><hr>


<form action="addassign.do" method='post'>

    <table>

        <tr>
            <td><b>Тема поручения</b></td>
            <td><input type='text' name='theme' class='form-control' size="20"/></td>
        </tr>
        <select name="executor">
            <option disabled selected>Выбор исполнителя</option>
            <c:forEach var="x" items="${employees}">
                <option value="${x.id}">${x.surname}&nbsp&nbsp${x.name}&nbsp&nbsp${x.middleName}</option>
            </c:forEach>
        </select>

        <tr>
            <td><b>Срок</b></td>
            <td><input type='text' name='time' class='form-control' size="20" /></td>
        </tr>

        <tr>
            <td><b>Текст</b></td>
            <td><textarea name='text' class='form-control' cols="40" rows="5"></textarea></td>
        </tr>

        <tr>
            <td></td>
            <td>
                <button type="submit" class="adminButton">Отправить</button>
            </td>
        </tr>

    </table>
</form>
</body>
</html>
