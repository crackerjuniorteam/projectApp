<#import "parts/common.ftl" as c>


<@c.page>
    <div><h1 align="center">Create pack</h1></div>
    <div>
        <form method="post">
            <input type="text" name="packName" placeholder="Введите название пака" size="30"/>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <div class="form-check m-2">
                <input class="form-check-input" type="radio" name="packPublic" value="public" checked>
                <label class="form-check-label" for="xxx">
                    <b>Public</b> <i>- будет виден всем пользователям</i>
                </label>
            </div>
            <div class="form-check m-2">
                <input class="form-check-input" type="radio" name="packPublic" value="private">
                <label class="form-check-label" for="xxx">
                    <b>Private</b> <i>- будет виден только вам</i>
                </label>
            </div>
            <button type="submit">Создать</button>
        </form>
    </div>
    <#if (checkCreate = 1)>
        <div class="alert alert-success m-3" role="alert">
            Pack successfully created
        </div>
    </#if>
    <#if (checkCreate = -1)>
        <div class="alert alert-danger m-3" role="alert">
            Such package already exists
        </div>
    </#if>
</@c.page>
