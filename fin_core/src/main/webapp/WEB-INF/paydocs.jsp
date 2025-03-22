<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>ПЛАТЕЖНЫЕ ДОКУМЕНТЫ</title>
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
        <th>Дебет</th>
        <th>Кредит</th>
        <th>Сумма</th>
        <th>Дата</th>
        <th>Назначение</th>
        <th>Состояние</th>
        <th>Причина отказа</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${payDocsList}" var="payDoc">
        <tr>
            <td>${payDoc.id}</td>
            <td>${payDoc.debet.accNum}</td>
            <td>${payDoc.credit.accNum}</td>
            <td>${payDoc.docSum}</td>
            <td>${payDoc.docDate}</td>
            <td>${payDoc.purpose}</td>
            <td>${payDoc.state}</td>
            <td>${payDoc.refuseReason}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>


</body>
</html>
