<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

<@c.page>
    <h2>User Profile</h2>
    <table>
        <tr>
            <rd><b>Аватар</b></rd>
            <td>
                <#if userByProfile.avatar??>
                    <img src="/img/${user.avatar!}" height="250">
                </#if>
            </td>
        </tr>
        <tr>
            <td><b>Username:</b></td>
            <td>${userByProfile.username}</td>
        </tr>
        <tr>
            <td><b>Email: </b></td>
            <td>${userByProfile.email!}</td>
        </tr>
        <tr>
            <td><b>First name: </b></td>
            <td>${userByProfile.firstName!}</td>
        </tr>
        <tr>
            <td><b>Last name: </b></td>
            <td>${userByProfile.lastName!}</td>
        </tr>
    </table>
    <div class="container my-3">
        <div class="row">
            <div class="col">
                <div class="card">
                    <div class="card-body">
                        <div class="card-title">Subscriptions - Подписки</div>
                        <h3 class="card-text">
                            <a href="/subscriptions/${userByProfile.id}/list">${subscriptionsCount!}</a>
                        </h3>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card">
                    <div class="card-body">
                        <div class="card-title">Subscribers - Подписчики</div>
                        <h3 class="card-text">
                            <a href="/subscribers/${userByProfile.id}/list">${subscribersCount!}</a>
                        </h3>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <#if (userByProfile.id = currentUserId)>
        <a href="/profileEdit">
            <button>Edit</button>
        </a>
    <#else>
        <#if isSubscriber>
            <a class="btn btn-info" href="/unsubscribe/${userByProfile.id}">Unsubscribe</a>
        <#else>
            <a class="btn btn-info" href="/subscribe/${userByProfile.id}">Subscribe</a>
        </#if>
    </#if>
</@c.page>