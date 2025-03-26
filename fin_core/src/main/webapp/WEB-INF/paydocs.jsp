<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" href="css/table_style.css">
    <title>ПЛАТЕЖНЫЕ ДОКУМЕНТЫ</title>
</head>
<body>

<table>
    <style type="text/css">
        <%@ include file="/WEB-INF/table_style.css"%>
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
