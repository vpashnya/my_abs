<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Депозиты</title>
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

        TD {
            background: #ffffff; /* Цвет фона */
            border: 1px solid #333; /* Граница вокруг ячеек */
            padding: 5px; /* Поля в ячейках */
        }
    </style>
    <thead>
    <tr>
        <th>id</th>
        <th>Наименование клиента</th>
        <th>Номер счета</th>
        <th>Срок в днях</th>
        <th>Ставка % годовых</th>
        <th>Дата открытия</th>
        <th>Сумма вклада</th>
        <th>Сумма на счете</th>
        <th>Статус договора</th>
        <th>Выплата вклада</th>


    </tr>
    </thead>
    <tbody>
    <c:forEach items="${depositList}" var="deposit">
        <tr>
            <td>${deposit.id}</td>
            <td>${deposit.clientName}</td>
            <td>${deposit.accountNum}</td>
            <td>${deposit.duration}</td>
            <td>${deposit.rate}</td>
            <td>${deposit.dateOpen}</td>
            <td>${deposit.amount}</td>
            <td>${deposit.accountRest}</td>
            <td>${deposit.status}</td>
            <td>${deposit.payTo}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>


</body>
</html>
