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
<div id = "demoFont">Личный кабинет: ${user}</div>
<td><a class="myButtonDel" href="exit.do"/>Выйти</a>    </td>
<td><a class="myButtonservice" href="holturchik.do"/>Найти холтурщика</a>&nbsp&nbsp${holturchik}</td>
<hr>
<a class="myButtonadd" href="newAssign.do"/>Создать поручение</a>
<h1 id="demoFont">Мои поручения:</h1>
<table class="blueTable"><tbody>
<tr>
    <td>as_id</td>
    <td>theme</td>
    <td>author</td>
    <td>executor</td>
    <td>time</td>
    <td>text</td>
    <td>execution</td>
</tr>
<c:forEach var="x" items="${myAssignments}">
<tr>
    <td>${x.id}</td>
    <td>${x.theme}</td>
    <td>${x.author}</td>
    <td>${x.executor}</td>
    <td>${x.time}</td>
    <td>${x.text}</td>
    <td>

        <c:choose>
            <c:when test = "${x.execution == false}">
                <c:out value = "На исполнении"/>
            </c:when>
            <c:when test = "${x.execution == true}">
                <c:out value = "Исполнено"/>
            </c:when>
            <c:otherwise>
                ...
            </c:otherwise>
        </c:choose>
    </td>
    <%--<td><a class="myButtonDel" href="deleteEmp${x.id}.do"/>Удалить</a>    </td>
    <td><a class="adminButton" href="editEmp${x.id}.do"/>Изменить</a>    </td>--%>
</tr>
</c:forEach>
    <tbody></table>

<h1 id="demoFont">Поручения мне:</h1>
<table class="blueTable"><tbody>
<tr>
    <td>as_id</td>
    <td>theme</td>
    <td>author</td>
    <td>executor</td>
    <td>time</td>
    <td>text</td>
    <td>execution</td>
</tr>
<c:forEach var="x" items="${forMeAssignments}">
<tr>
    <td>${x.id}</td>
    <td>${x.theme}</td>
    <td>${x.author}</td>
    <td>${x.executor}</td>
    <td>${x.time}</td>
    <td>${x.text}</td>
    <td>
        <c:choose>
            <c:when test = "${x.execution == false}">
                <c:out value = "На исполнении"/>
            </c:when>
            <c:when test = "${x.execution == true}">
                <c:out value = "Исполнено"/>
            </c:when>
            <c:otherwise>
                ...
            </c:otherwise>
        </c:choose>
    </td>
        <td><a class="adminButton" href="executeAssign${x.id}.do"/>Исполнить</a>    </td>
</tr>
</c:forEach>
    <tbody></table>



<h1 id="demoFont">Исполненные мною:</h1>
<table class="blueTable"><tbody>
<tr>
    <td>as_id</td>
    <td>theme</td>
    <td>author</td>
    <td>executor</td>
    <td>time</td>
    <td>text</td>
    <td>execution</td>
</tr>
<c:forEach var="x" items="${forMeDoneAssignments}">
<tr>
    <td>${x.id}</td>
    <td>${x.theme}</td>
    <td>${x.author}</td>
    <td>${x.executor}</td>
    <td>${x.time}</td>
    <td>${x.text}</td>
    <td>
        <c:choose>
            <c:when test = "${x.execution == false}">
                <c:out value = "На исполнении"/>
            </c:when>
            <c:when test = "${x.execution == true}">
                <c:out value = "Исполнено"/>
            </c:when>
            <c:otherwise>
                ...
            </c:otherwise>
        </c:choose>
    </td>
</tr>
</c:forEach>
    <tbody></table>

</body>
</html>
