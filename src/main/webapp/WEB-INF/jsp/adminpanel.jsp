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
    <a class="adminButton" href="start.do"/>Главная страница</a><br>
        

        <h1 id="demoFont">Сотрудники:</h1>
        <table class="blueTable"><tbody>
        <tr>
            <td>emp_id</td>
            <td>name</td>
            <td>middleName</td>
            <td>surname</td>
            <td>position</td>
        </tr>
        <c:forEach var="x" items="${employees}">
        <tr>
            <td>${x.id}</td>
            <td>${x.name}</td>
            <td>${x.middleName}</td>
            <td>${x.surname}</td>
            <td>${x.position}</td>
            <%--<td>${x.department}</td>--%>
            <td><a class="myButtonDel" href="deleteEmp${x.id}.do"/>Удалить</a>    </td>
            <td><a class="adminButton" href="editEmp${x.id}.do"/>Изменить</a>    </td>


        </tr>
        </c:forEach>
            <tbody></table>
        <td><a class="myButtonadd" href="newEmp.do"/>Добавить сотрудника</a>    </td>


        <h1 id="demoFont">Подразделения:</h1>
        <table class="blueTable"><tbody>
        <tr>
            <td>dep_id</td>
            <td>name</td>
            <td>contacts</td>
            <td>manager</td>
            <td>employeeSet</td>
        </tr>
        <c:forEach var="x" items="${departments}">
        <tr>
            <td>${x.id}</td>
            <td>${x.name}</td>
            <td>${x.contacts}</td>
            <td>${x.manager}</td>
            <td>${x.employeeSet}</td>
        </tr>
        </c:forEach>
            <tbody></table>

        <h1 id="demoFont">Организации:</h1>
        <table class="blueTable"><tbody>
        <tr>
            <td>org_id</td>
            <td>name</td>
            <td>legalAddress</td>
            <td>physicalAddress</td>
            <td>manager</td>
            <td>departmentSet</td>
        </tr>
        <c:forEach var="x" items="${organizations}">
        <tr>
            <td>${x.id}</td>
            <td>${x.name}</td>
            <td>${x.legalAddress}</td>
            <td>${x.physicalAddress}</td>
            <td>${x.manager}</td>
            <td>${x.departmentSet}</td>
        </tr>
        </c:forEach>
            <tbody></table>

    <td><a class="myButtonservice" href="trudyaga.do"/>Найти трудягу</a>&nbsp&nbsp${trudyaga}</td>

    </body>
</html>
