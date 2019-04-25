<#import "parts/common.ftl" as c>

<@c.page>
    User editor

    <form action="/profileEdit" method="post" enctype="multipart/form-data">
        <table>
            <tbody>
            <tr>
                <td><b>Аватар: </b></td>
                <td><input type="file" name="file"></td>
            </tr>
            <tr>
                <td><b>Username: </b></td>
                <td><input type="text" name="username" value="${user.username}"></td>
            </tr>
            <tr>
                <td><b>Password: </b></td>
                <td><input type="password" name="password" placeholder="******"></td>
            </tr>
            <tr>
                <td><b>Email: </b></td>
                <td><input type="email" name="email" value="${user.email!}"></td>
            </tr>
            <tr>
                <td><b>FirstName: </b></td>
                <td><input type="text" name="firstName" value="${user.firstName!}"></td>
            </tr>
            <tr>
                <td><b>LastName: </b></td>
                <td><input type="text" name="lastName" value="${user.lastName!}"></td>
            </tr>
            </tbody>
        </table>
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <button type="submit">Save</button>
    </form>
</@c.page>
