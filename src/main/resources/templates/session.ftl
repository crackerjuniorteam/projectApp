<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <meta name="_csrf" content="${_csrf.token}"/>
    <!-- default header name is X-CSRF-TOKEN -->
    <meta name="_csrf_header" content="${_csrf.headerName}"/>

    <meta name="pack" content="${packName}"/>


    <link href="/css/session-style.css" rel="stylesheet">
    <!--<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">-->

    <title>Memory</title>
</head>
<body>

<div id="flashcard-app" class="container">
    <div v-if="this.End">
        <div class="jumbotron">
            <h1 class="display-4">You have memorized all the cards!</h1>
            <p class="lead">This is a simple hero unit, a simple jumbotron-style component for calling extra attention to featured content or information.</p>
            <p class="lead">
                <a class="btn btn-primary btn-lg" href="/" role="button">Main Page</a>
            </p>
        </div>
    </div>
    <div v-if="!this.End">
        <h1>Flashcard App!</h1>


        <ul class="flashcard-list" style="user-select: none">
            <li v-on:click="toggleCard(card)" id="list">
                <transition name="flip">
                    <p class="card" v-if="!this.flipped" key="question">
                        {{card.question}}
                    </p>
                    <p class="card" v-else key="answer">
                        {{card.answer}}
                    </p>
                </transition>
            </li>
        </ul>

        <div  v-if="this.flipped">
            <button class="btn btn-outline-success" id="remember" v-on:click="saveRemember()">Remember</button>
            <button class="btn btn-outline-warning" id="!sure" v-on:click="saveDoubt()">Not Sure</button>
            <button class="btn btn-outline-danger" id="don't" v-on:click="saveDontRemember()">Don't Remember</button>
        </div>
    </div>
    {{cards}}

</div>

<script src="https://unpkg.com/vue"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script src="/js/main.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
</body>
</html>


