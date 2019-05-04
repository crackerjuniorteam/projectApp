<#import "parts/common.ftl" as c>


<@c.page>
    <div>
        <form method="post">
            <input type="text" name="packName" placeholder="Введите название пака" size="30"/>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit">Создать</button>
        </form>
    </div>
    <#if (checkCreate = 1)>
        <div class="alert alert-success m-3" role="alert">
            Pack successfully created
        </div>
    </#if>
    <#if (checkCreate = -1)>
        <div class="alert alert-danger m-3" role="alert">
            Such package already exists
        </div>
    </#if>
</@c.page>
