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
            <td><b>Last name: </b></td><td>${userByProfile.lastName!}</td>
        </tr>
    </table>
    <#if (userByProfile.id = currentUserId)>
    <a href="/profileEdit">
        <button>Edit</button>
    </a>
    </#if>
</@c.page>