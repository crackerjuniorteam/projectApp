<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

<@c.page>

<div class="card-columns">
    <#list cards as card>
        <div class="card my-3">
            <div class="card my-3">
                <div class="card p-1">
                    <span><b>Вопрос:</b></span>
                    <span>${card.question!}</span>
                </div>
                <div id="Answer">
                    <div v-if="!visible" class="card p-1">
                        <span><b>Ответ:</b></span>
                        <i>${card.answer!}</i>
                    </div>
                    <button v-on:click="visible=!visible">{{visible?'Показать ответ':'Скрыть ответ'}}</button>
                </div>
                <form method="post">
                    <button type="submit" class="btn btn-success mt-5 mb-5">
                        Помню
                    </button>
                    <button type="submit" class="btn btn-warning mt-5 mb-5">
                        Сомневаюсь
                    </button>
                    <button type="submit" class="btn btn-danger mt-5 mb-5">
                        Не помню
                    </button>
                </form>
            </div>
        </div>
    <#else>
        No Card
    </#list>

    <div id="app">
        {{cards}}
    </div>
</div>
    <script src="https://unpkg.com/vue"></script>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    <script>
        Vue.component('button-answer_controller', {
            data: function () {
                return {
                    visible: true
                }
            },
            template: '<button v-on:click="visible=!visible">{{visible?\'Скрыть ответ\':\'Показать ответ\'}}</button>'
        })
        var app = new Vue({
            el: '#Answer',
            data: {
                visible: true
            }
        });
    </script>
    <script>
        const url = "/rest/session/test";
        const vm = new Vue({
            el: '#app',
            data: {
                cards: []

            },

            mounted() {

                axios.get(url).then(response => {

                    this.cards = response.data
                    for (var i = 0;i<this.cards.length;i++){
                        this.cards[i].flipped = false;
                    }
                })
            }
        });
    </script>
</@c.page>