<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>КАССЫ</title>
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
        <th>Счет кассы</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${cashPointList}" var="cashPoint">
        <tr>
            <td>${cashPoint.id}</td>
            <td>${cashPoint.pointName}</td>
            <td>${cashPoint.pointAccountNum}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>


</body>
</html>
