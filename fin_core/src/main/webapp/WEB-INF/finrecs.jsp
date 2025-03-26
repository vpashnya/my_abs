<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" href="css/table_style.css">
    <title>КЛИЕНТЫ</title>
</head>
<body>

<table>
    <style type="text/css">
        <%@ include file="/WEB-INF/table_style.css"%>
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
