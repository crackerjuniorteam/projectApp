<#import "parts/common.ftl" as c>
<#import "parts/pager.ftl" as p>

<@c.page>
    <div><h1>Your packs</h1></div>
    <div class="card-columns">
        <#list page.content as p>
            <div class="card m-3">
                <div class="card-body" align="center">
                    <h5><p class="card-title">${p.name}</p></h5>
                    <a class="card-text" href="packs/${p.id}">Edit pack</a>
                </div>
                <div class="card-footer" align="center">
                    <a href="session/${p.id}">Начать сессию!</a>
                </div>
            </div>
        <#else>
            No Card
        </#list>
    </div>
    <@p.pager url page/>
</@c.page>
