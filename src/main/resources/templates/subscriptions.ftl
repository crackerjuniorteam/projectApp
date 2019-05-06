<!-- шаблон для подписок -->
<#import "parts/common.ftl" as c>

<@c.page>
    <h3>${userChannel.username}</h3>
    <#if (type = "subscribers")>
        <div>Subscribers - Подписчики</div>
    <#else>
        <div>Subscriptions - Подписки</div>
    </#if>
    <ul class="list-group">
        <#list users as user>
            <li class="list-group-item">
                <a href="/profile/${user.username}">${user.getUsername()}</a>
            </li>
        </#list>
    </ul>
</@c.page>