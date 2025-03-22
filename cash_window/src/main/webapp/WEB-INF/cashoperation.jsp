<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>КАССОВЫЕ ОПЕРАЦИИ</title>
</head>
<body>

<table>
    <style type="text/css">
        TABLE {
            /*width: 100%;*/ /* Ширина таблицы */
            border: 1px solid #399; /* Граница вокруг таблицы */
            border-spacing: 1px 1px; /* Расстояние между границ */
        }

        TH {
            background: #888888; /* Цвет фона */
            border: 1px solid #333; /* Граница вокруг ячеек */
            padding: 5px; /* Поля в ячейках */
        }
        TD{
            background: #ffffff; /* Цвет фона */
            border: 1px solid #333; /* Граница вокруг ячеек */
            padding: 5px; /* Поля в ячейках */
        }
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
