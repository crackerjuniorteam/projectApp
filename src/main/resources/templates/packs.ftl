<#import "parts/common.ftl" as c>
<#import "parts/pager.ftl" as p>

<@c.page>
    <div><h1>Your packs</h1></div>
    <div class="card-columns">
        <#list page.content as p>
            <div class="card m-3">
                <div class="card-body">
                    <h5 class="card-title">${p.name}</h5>
                    <a href="session/${p.id}">Начать сессию!</a>
                    <a href="packs/${p.id}">Edit pack</a>

                </div>
            </div>
        <#else>
            No Card
        </#list>
    </div>
    <@p.pager url page/>
</@c.page>
