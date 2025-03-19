<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>


<table>
    <style type="text/css">
        TABLE {
            /*width: 100%;*/ /* Ширина таблицы */
            border: 1px solid #399; /* Граница вокруг таблицы */
            border-spacing: 7px 5px; /* Расстояние между границ */
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
    <input type="submit" value="Перейти в следующий опер.день" />
</form>

<br><br>

СТАТИСТИКА ПО БАНКУ

<br><br>

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