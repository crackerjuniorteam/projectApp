<#import "parts/common.ftl" as c>
<#import "parts/pager.ftl" as p>

<@c.page>
    <div>Ниже находится список ваших паков</div>
    <div class="card-columns">
        <#list page.content as p>
            <div class="card m-3">
                <div class="card-body">
                    <h5 class="card-title">Название</h5>
                    <p class="card-text">${p.name}</p>
                    <a href="session/${p.id}">Начать сессию!</a>
                </div>
            </div>
        <#else>
            No Card
        </#list>
    </div>
    <@p.pager url page/>
</@c.page>
