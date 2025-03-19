<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>СЧЕТА</title>
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
        <th>Тип счета</th>
        <th>Номер счета</th>
        <th>Клиент</th>
        <th>Остаток</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${accountList}" var="ac">
        <tr>
            <td>${ac.account.id}</td>
            <td>${ac.account.accType}</td>
            <td>${ac.account.accNum}</td>
            <td>${ac.account.client.fullName}</td>
            <td>${ac.rest}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>


</body>
</html>
