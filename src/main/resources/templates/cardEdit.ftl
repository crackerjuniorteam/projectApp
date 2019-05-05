<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

<@c.page>
    <style>
        .header-h1 h1 {
            position: relative;
            padding-bottom: .5rem;
            font-size: 1.5rem;
            text-transform: uppercase;
            text-align: center;
            color: #00838f;
        }
        .header-h1 h1::before {
            content: "";
            position: absolute;
            border-bottom: 2px solid #00838f;
            bottom: .25rem;
            left: 50%;
            width: 30%;
            transform: translateX(-50%);
        }
        .header-h1 h1::after {
            content: "";
            position: absolute;
            border-bottom: 2px solid #00838f;
            bottom: 0;
            left: 50%;
            width: 15%;
            transform: translateX(-50%);
        }
        .header-h1-left h1 {
            text-align: left;
        }
        .header-h1-left h1::before,
        .header-h1-left h1::after {
            left: 0;
            transform: translateX(0);
        }
        .header-h1-right h1 {
            text-align: right;
        }
        .header-h1-right h1::before,
        .header-h1-right h1::after {
            left: unset;
            right: 0;
            transform: translateX(0);
        }
    </style>

    <div class="header-h1">
        <h1>Редактирование карточки</h1>
    </div>
    <#if message??>
        <div class="alert alert-secondary" role="alert">
            ${message!}
        </div>
    </#if>
    <div class="form-group mt-3">
        <form method="post">
            <div class="form-group">
                <input type="text" class="form-control ${(questionError??)?string('is-invalid','')}" value="<#if card??>${card.question}</#if>" name="question" placeholder="Введите вопрос"/>
                <#if questionError??>
                    <div class="invalid-feedback">
                        ${questionError}
                    </div>
                </#if>
            </div>
            <div class="form-group">
                <input type="text" class="form-control ${(answerError??)?string('is-invalid','')}" value="<#if card??>${card.answer}</#if>" name="answer" placeholder="Введите ответ"/>
                <#if answerError??>
                    <div class="invalid-feedback">
                        ${answerError}
                    </div>
                </#if>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Сохранить</button>
            </div>
        </form>
    </div>
</@c.page>