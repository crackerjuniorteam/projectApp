<#import "parts/common.ftl" as c>


<@c.page>
    <div>
        <form method="post">
            <input type="text" name="elem" placeholder="Введите название пака" size="30"/>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit">Создать</button>
        </form>
    </div>
    ${message!}
</@c.page>
