<#import "parts/common.ftl" as c>

<@c.page>
    <h2>User Profile</h2>
    <b>Id: </b>${user.id}<br>
    <b>Username: </b>${user.username}<br>
    <b>Email: </b>${user.email!}<br>
    <b>First name: </b>${user.firstName!}<br>
    <b>Last name: </b>${user.lastName!}<br>
    <a href="/profileEdit"><button>Edit</button></a>
</@c.page>