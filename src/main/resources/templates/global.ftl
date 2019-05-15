<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<#import "parts/pager.ftl" as p>

<@c.page>
    <p>
        Global Packs
        Popular categories
        Languages
        Art, sciences and trivia
    </p>
    <@p.pager url page/>
    <div class="card-columns">
        <#list page.content as p>
            <div class="card m-3">
                <div class="card-body">
                    <h5 class="card-title">Название</h5>
                    <p class="card-text">${p.name}</p>
                </div>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">Likes: ${p.likes!}</li>
                    <li class="list-group-item">Date created: ${p.created!}</li>
                </ul>
                <div class="card-body">
                    <div class="card-link"></div>
                    <form method="post">
                        <button type="submit" class="btn btn-outline-info">Добавить к себе</button>
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <input type="hidden" name="id" value="${p.id}"/>
                    </form>
                </div>
            </div>
        <#else>
            No Card
        </#list>
    </div>
    <@p.pager url page/>
</@c.page>