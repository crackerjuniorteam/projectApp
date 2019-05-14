let token = document.querySelector("[name~=_csrf][content]").content;

axios.defaults.headers.common = {
    'X-Requested-With': 'XMLHttpRequest',
    'X-CSRF-TOKEN': token,
};

const url = "/rest/session/test";
let index = 0;

const vm = new Vue({
    el: '#flashcard-app',
    data: {
        cards: [],
        newFront: '',
        newBack: '',
        error: false,
        flipped: false,
        postBody: '',
        errors: []
    },
    computed:{
        card: function () {
            console.log(this.flipped);
            console.log(this.cards);
            return this.cards[index];
        }
    },
    created() {
        axios.get(url).then(response => {

            this.cards = response.data

        });
    },

    methods: {
        toggleCard: function(card){
            this.flipped = !this.flipped;
        },
        saveRemember: function () {
            console.log("SaveRemember");
            this.post(1);
            this.next();
        },
        saveDoubt: function () {
            console.log("SaveDoubt");
            this.post(2);
            this.next();
        },
        saveDontRemember: function () {
            console.log("SaveDontRemember");
            this.post(3);
            this.next();
        },
        next: function(){
            console.log(index);
            if (index > this.cards.length - 2) {
                alert("Карты кончились")
            }
            else {
                this.flipped = !this.flipped;
                index = index + 1;
                this.card = this.cards[index];
            }

        },
        post: function (reply) {
            axios.post(url,{
                id: this.card.id,
                answer: reply
            }).then(response => {}).catch(e => {
                this.errors.push(e)
            })
        }

    }
});