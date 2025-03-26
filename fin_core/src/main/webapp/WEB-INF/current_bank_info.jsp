<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>ИНФОРМАЦИЯ О БАНКЕ</title>
</head>
<body>


<table>
    <style type="text/css">
        <%@ include file="/WEB-INF/table_style.css"%>
    </style>
    <thead>
    <tr>
        <th>БАНК</th>
        <th>БИК</th>
        <th>КОР.СЧЕТ</th>
        <th>ОПЕР.ДЕНЬ</th>
    </tr>
    </thead>
    <tbody>

    <tr>
        <td>${bankName}</td>
        <td>${bankCode}</td>
        <td>${bankCorAcc}</td>
        <td>${bankOperDay}</td>
    </tr>

    </tbody>
</table>

<br><br>

<form method="POST">
    <param name="nextday" value="true"/>
    <input type="submit" value="Перейти в следующий опер.день"/>
</form>

<br><br>

СТАТИСТИКА ПО БАНКУ

<br><br>

<table>
    <style type="text/css">
        <%@ include file="/WEB-INF/table_style.css"%>
    </style>
    <thead>
    <tr>
        <th>ОБЪЕКТ</th>
        <th>КОЛ-ВО</th>

    </tr>
    </thead>
    <tbody>

    <tr>
        <td><a href="client">КЛИЕНТЫ</a></td>
        <td>${clientsCount}</td>
    </tr>

    <tr>
        <td><a href="account">СЧЕТА</a></td>
        <td>${accountsCount}</td>
    </tr>


    <tr>
        <td><a href="paydocument">ПЛАТЕЖНЫЕ ДОКУМЕНТЫ</a></td>
        <td>${payDocsCount}</td>
    </tr>


    <tr>
        <td><a href="finrecord">ПРОВОДКИ</a></td>
        <td>${finRecordsCount}</td>
    </tr>


    </tbody>
</table>


</body>
</html>