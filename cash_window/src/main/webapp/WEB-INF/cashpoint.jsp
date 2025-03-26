<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>КАССЫ</title>
    <link rel="stylesheet" href="table_style.css">
</head>
<body>

<table>
    <style type="text/css">
        <%@ include file="/WEB-INF/table_style.css"%>
    </style>
    <thead>
    <tr>
        <th>id</th>
        <th>Название кассы</th>
        <th>Счет кассы</th>
        <th>Кол-во документов</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${cashPointList}" var="cashPoint">
        <tr>
            <td>${cashPoint.cashPoint.id}</td>
            <td>${cashPoint.cashPoint.pointName}</td>
            <td>${cashPoint.cashPoint.pointAccountNum}</td>
            <td>${cashPoint.count}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<a href="cashoperation">Кассовые документы</a>

</body>
</html>
