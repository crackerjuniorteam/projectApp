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



     -->
