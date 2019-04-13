<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
    <@l.logout />
    <div>
        <ul>
            <li><a href="/profile"><b>Профиль</b></a></li>
            <li><a href="/createpack"><b>Создать Pack</b></a></li>
            <li><a href="/packs"><b>Паки</b></a></li>
        </ul>
    </div>
</@c.page>


<!--  <div>
        <span><a href="/user">User List</a></span>
    </div>

    <div>Ниже находится список ваших паков</div>
    #list packs as pack>
        <div>
            <a href="packs/$pack.name}">$pack.name!}</a>
        </div>
    /#list>

     -->
