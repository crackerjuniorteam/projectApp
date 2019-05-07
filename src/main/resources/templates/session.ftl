<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">



    <link href="/css/session-style.css" rel="stylesheet">
    <#--    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">.-->

    <title>Memory</title>
</head>
<body>

<div id="flashcard-app" class="container">

    <h1>Flashcard App!</h1>


    <ul class="flashcard-list" style="user-select: none">
        <li v-on:click="toggleCard(card)" id="list">
            <transition name="flip">
                <p class="card" v-if="!card.flipped" key="question">
                    {{card.question}}
                </p>
                <p class="card" v-else key="answer">
                    {{card.answer}}
                </p>
            </transition>
        </li>
    </ul>


    <div  v-if="card.flipped">
        <button class="button-card btn btn-success" id="remember" onclick="next ()">Remember</button>
        <button class="button-card btn btn-danger" id="don't" onclick="next ()">Don't Remember</button>
        <button class="button-card btn btn-warning" id="!sure" onclick="next ()">Not Sure</button>
    </div>
    {{cards}}

</div>
</div>
<script src="https://unpkg.com/vue"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script src="/js/main.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"  crossorigin="anonymous"></script>

</body>
</html>


