<#include "security.ftl">
<#import "login.ftl" as l>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="navbar-brand">Memory</div>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <#if name != "Guest">
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/packs">Packs</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/createPack">Create Pack</a>
                </li>
            </ul>
        </div>
        <div class="navbar-text mr-1">
            <div class="nav-item">
                <!-- user.name это из security.ftl-->
                <a class="nav-link" href="/profile/${name}">Profile</a>
            </div>
        </div>
        <@l.logout />
    <#else>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/">Home</a>
                </li>
            </ul>
        </div>
        <div class="navbar-text mr-1">
            <a href="/login">
                <button type="submit" class="btn btn-primary">Log in</button>
            </a>
            <a href="/reg">
                <button type="submit" class="btn btn-primary">Sign Up</button>
            </a>
        </div>
    </#if>

</nav>