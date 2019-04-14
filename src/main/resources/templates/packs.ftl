<#import "parts/common.ftl" as c>

<@c.page>
    <div>Ниже находится список ваших паков</div>
    <#list packs as pack>
    <ul>
        <li><a href="packs/${pack.name}">${pack.name!}</a></li>
    </ul>
    </#list>
</@c.page>
