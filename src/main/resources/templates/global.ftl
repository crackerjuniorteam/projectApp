<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<#import "parts/pager.ftl" as p>
<#include "parts/security.ftl">

<@c.page>
    <@p.pager url page/>
    <div class="card-columns">
        <#list page.content as p>
            <div class="card m-3" style="width: 18rem;" align="center">
                <div class="card-header">
                    <h3 class="card-title">${p.name}</h3>
                </div>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">Likes: ${p.likes!}</li>
                    <li class="list-group-item">Date created: ${p.created!}</li>
                </ul>
                <div class="card-body">
                    <div class="card-link"></div>
                    <form method="post" action="/global">
                        <#if !user.containsPackName(p.name)>
                            <button type="submit" class="btn btn-outline-info">Добавить к себе</button>
                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                            <input type="hidden" name="id" value="${p.id}"/>
                        <#else>
                            <button disabled="disabled" type="submit" class="btn btn-success">Уже добавлен</button>
                        </#if>
                    </form>
                </div>
            </div>
        <#else>
            No Card
        </#list>
    </div>
    <@p.pager url page/>
</@c.page>