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
    <div>Ниже находится список ваших паков</div>
    <#list packs as pack>
        <div>
            <a href="packs/${pack.name}">${pack.name!}</a>
        </div>
    </#list>
</@c.page>
