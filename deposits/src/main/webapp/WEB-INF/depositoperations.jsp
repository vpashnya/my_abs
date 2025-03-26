<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>ОПЕРАЦИИ ДЕПОЗИТОВ</title>
</head>
<body>

<table>
    <style type="text/css">
        <%@ include file="/WEB-INF/table_style.css"%>
    </style>
    <thead>
    <tr>
        <th>id</th>
        <th>id депозита</th>
        <th>Дата операции</th>
        <th>Сумма операции</th>
        <th>Плат. документ</th>
        <th>Кассовая операция</th>
        <th>Назначение операции</th>
        <th>Состояние</th>
        <th>Причина отказа</th>
    </tr>

    </thead>

    <tbody>
    <c:forEach items="${depOperList}" var="depOper">
        <tr>
            <td>${depOper.id}</td>
            <td>${depOper.deposit.id}</td>
            <td>${depOper.operDate}</td>
            <td>${depOper.operSum}</td>
            <td>${depOper.payDocId}</td>
            <td>${depOper.cashOperId}</td>
            <td>${depOper.purpose}</td>
            <td>${depOper.state}</td>
            <td>${depOper.refuseReason}</td>

        </tr>
    </c:forEach>
    </tbody>
</table>


</body>
</html>
