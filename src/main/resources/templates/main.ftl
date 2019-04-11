<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
    <@l.logout />
    <div>
        <b>Создать Pack</b>
        <a href="/createpack">Перейти</a>
    </div>

    <div>
        <span><a href="/user">User List</a></span>
    </div>
    <div>
        <form method="post">
            <input type="text" name="question" placeholder="Введите вопрос"/>
            <input type="text" name="answer" placeholder="Ответ">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit">Добавить</button>
        </form>
    </div>
    <div>Список сообщений</div>
    <form method="get" action="/main">
        <input type="text" name="filter" value="${filter}">
        <button type="submit">Найти</button>
    </form>
    <#list cards as card>
        <div>
            <b>${card.id!}</b>
            <span>${card.question!}</span>
            <i>${card.answer!}</i>
            <strong>${card.authorName!}</strong>
        </div>
    <#else>
        No message
    </#list>
</@c.page>
