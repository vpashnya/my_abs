<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" href="css/table_style.css">
    <title>СЧЕТА</title>
</head>
<body>

<table>
    <style type="text/css">
        <%@ include file="/WEB-INF/table_style.css"%>
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
    <c:forEach items="${accountList}" var="account">
        <tr>
            <td>${account.id}</td>
            <td>${account.accType}</td>
            <td>${account.accNum}</td>
            <td>${account.client.fullName}</td>
            <td>${account.rest}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>


</body>
</html>
