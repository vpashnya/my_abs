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
        <th>Полное имя</th>
        <th>ИНН</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${clientList}" var="client">
        <tr>
            <td>${client.id}</td>
            <td>${client.fullName}</td>
            <td>${client.inn}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>


</body>
</html>
