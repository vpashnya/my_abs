<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>

<br><br>
<a href="deposit">Просмотреть список депозитов</a>

<br><br>
<a href="depositoperation">Просмотреть список операций депозитов</a>

<br><br>
<br><br>
=========================================================================================================

<form method="POST" accept-charset="UTF-8">
    <table>
        <tr>
            <td>ИНН:</td>
            <td><input name="inn"/></td>
        </tr>

        <tr>
            <td>Наименование клиента:</td>
            <td><input name="name"/></td>
        </tr>

        <tr>
            <td>Сумма вклада:</td>
            <td><input name="sum"/></td>
        </tr>

        <tr>
            <td>Процентная ставка:</td>
            <td><select name="prc">
                <option>1 %</option>
                <option>5 %</option>
                <option>10 %</option>
                <option>15 %</option>
                <option>20 %</option>
                <option>25 %</option>
            </select>
            </td>
        </tr>

        <tr>
            <td>Срок в днях:</td>
            <td><select name="duration">
                <option>1</option>
                <option>3</option>
                <option>7</option>
                <option>14</option>
                <option>28</option>
                <option>45</option>
                <option>61</option>
            </select>
            </td>
        </tr>

        <tr>
            <td>Выплата</td>
            <td><input type="radio" name="payto" value="cash" checked/>наличными
                <input type="radio" name="payto" value="no_cash"/>безналичные
            </td>
        </tr>

    </table>

    <br><br>

    <input type="submit" value="Открыть вклад"/>
</form>
</body>
</html>