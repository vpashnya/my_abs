<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>КЛИЕНТЫ</title>
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
        <th>Счет</th>
        <th>Дата</th>
        <th>Сумма</th>
        <th>Тип</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${recList}" var="record">
        <tr>
            <td>${record.id}</td>
            <td>${record.account.accNum}</td>
            <td>${record.recDate}</td>
            <td>${record.recSum}</td>
            <td>${record.recType}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>


</body>
</html>
