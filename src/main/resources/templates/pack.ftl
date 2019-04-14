<#import "parts/common.ftl" as c>


<@c.page>
    <style type="text/css">
        TABLE {
            width: 300px; /* Ширина таблицы */
            border-collapse: collapse; /* Убираем двойные линии между ячейками */
        }
        TD, TH {
            padding: 3px; /* Поля вокруг содержимого таблицы */
            border: 1px solid black; /* Параметры рамки */
        }
        TH {
            background: #b0e0e6; /* Цвет фона */
        }
    </style>
    <h2>User Pack</h2>
    <b>Name: </b>${pack.name!}<br>
    <b>Like: </b>${pack.likes!}<br>
    <b>Date created: </b>${time!}<br>
    <form method="post">
        <input type="text" name="question" placeholder="Введите вопрос" size="30"/>
        <input type="text" name="answer" placeholder="Введите ответ" size="30"/>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit">Создать карточку</button>
    </form>
    <br>
    <table>
        <thead>
        <tr>
            <td><b>Вопрос</b></td>
            <td><b>Ответ</b></td>
        </tr>
        </thead>
        <#list cards as card>
            <tr>
            <td>${card.question!}</a></td>
            <td>${card.answer!}</a></td>
            </tr>
        <#else>
            <b>Карточек нет.</b><br>
        </#list>
    </table>
</@c.page>
