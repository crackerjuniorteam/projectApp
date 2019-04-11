<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
    <@l.logout />
    <div>
        <b>Создать Pack</b> <br>
        <a href="/createpack">Перейти</a>
    </div>

    <div>
        <span><a href="/user">User List</a></span>
    </div>
    <#list packs as pack>
        <div>
            ${pack.name!}
        </div>
    </#list>
</@c.page>
