<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>

    <meta name="pack" content="${packId}"/>



    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link href="/css/session-style.css" rel="stylesheet">
    <title>Memory</title>
</head>
<body>
<#include "parts/navbar.ftl"/>

<div id="flashcard-app" class="container">
    <div class="card-div">

        <button type="button" class="btn btn-outline-danger end-session" v-on:click="endSession()">End Session</button>

        <h3 class="text-muted" v-if="this.needToRepeat" key="counter">Cards remaining: {{cardsToRepeat - cardsCount}}</h3>
        <h3 class="text-success" v-else key="goodJob">You have repeated all cards planned for today!</h3>

        <ul class="flashcard-list">
            <transition name="flip">
                <li v-on:click="toggleCard(card)" id="list" v-if="!this.flipped" key="question">

                    <p class="card" >
                        {{sessionDTO.question}}
                    </p>

                </li>
                <li v-on:click="toggleCard(card)" id="list" v-else key="answer">

                    <p class="card" >
                        {{sessionDTO.answer}}
                    </p>

                </li>
            </transition>
        </ul>
        <div class="button-choice">
            <div  v-if="this.flipped">
                <button class="btn btn-success" id="remember" v-on:click="saveRemember()">Remember</button>
                <button class="btn btn-warning" id="!sure" v-on:click="saveDoubt()">Not Sure</button>
                <button class="btn btn-danger" id="don't" v-on:click="saveDontRemember()">Don't Remember</button>
            </div>
        </div>



    </div>
</div>

<script src="https://unpkg.com/vue"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script src="/js/main.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
</body>
</html>


