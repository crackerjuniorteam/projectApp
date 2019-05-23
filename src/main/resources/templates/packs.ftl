<#import "parts/common.ftl" as c>
<#import "parts/pager.ftl" as p>

<@c.page>
    <div><h1  align="center">Your packs</h1></div>
    <div class="card-columns">
        <#list page.content as p>
            <div class="card m-3" style="width: 18rem;" align="center">
                <div class="card-header">
                    <h3 class="card-title">${p.name}</h3>
                </div>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item"><a class="card-text" href="packs/${p.id}">Edit pack</a></li>
                    <li class="list-group-item"><a href="session/${p.id}">Начать сессию!</a></li>
                </ul>
            </div>
        <#else>
            No Card
        </#list>
    </div>
    <@p.pager url page/>
</@c.page>
