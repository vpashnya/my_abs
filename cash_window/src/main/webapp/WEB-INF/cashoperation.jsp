<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>КАССОВЫЕ ОПЕРАЦИИ</title>

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
        <th>Счет операции</th>
        <th>Сумма операции</th>
        <th>Пополнение/снятие</th>
        <th>Назначение</th>
        <th>Состояние</th>
        <th>Причина отказа</th>

    </tr>
    </thead>
    <tbody>
    <c:forEach items="${cashOperationList}" var="cashOperation">
        <tr>
            <td>${cashOperation.id}</td>
            <td>${cashOperation.cashPoint.pointName}</td>
            <td>${cashOperation.accountNum}</td>
            <td>${cashOperation.sumDoc}</td>
            <td>${cashOperation.direction}</td>
            <td>${cashOperation.purpose}</td>
            <td>${cashOperation.state}</td>
            <td>${cashOperation.refuseReason}</td>

        </tr>
    </c:forEach>
    </tbody>
</table>


</body>
</html>
