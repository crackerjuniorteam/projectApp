<#import "parts/common.ftl" as c>

<@c.page>
    <h2>User Profile</h2>
    <table>
    <tr>
        <rd><b>Аватар</b></rd>
        <td>
            <#if user.avatar??>
                <img src="/img/${user.avatar!}" height="250">
            </#if>
        </td>
    </tr>
    <tr>
        <td><b>Username:</b></td>
        <td>${user.username}</td>
    </tr>
    <tr>
        <td><b>Email: </b></td>
        <td>${user.email!}</td>
    </tr>
    <tr>
        <td><b>First name: </b></td>
        <td>${user.firstName!}</td>
    </tr>
        <tr>
            <td><b>Last name: </b></td><td>${user.lastName!}</td>
        </tr>
    </table>
    <a href="/profileEdit">
        <button>Edit</button>
    </a>
</@c.page>