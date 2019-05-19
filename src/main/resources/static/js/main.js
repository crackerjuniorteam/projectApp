let token = document.querySelector("[name~=_csrf][content]").content;

axios.defaults.headers.common = {
    'X-Requested-With': 'XMLHttpRequest',
    'X-CSRF-TOKEN': token,
};

let url = "/rest/session/" + document.querySelector("[name~=pack][content]").content;
let index = 0;

const vm = new Vue({
    el: '#flashcard-app',
    data: {
        cards: [],
        error: false,
        flipped: false,
        errors: [],
        isActive: true
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
        endSession: function(){
            this.isActive = !this.isActive;
            url = "/rest/session/end/" + document.querySelector("[name~=pack][content]").content;
            axios.post(url,{
                id: this.card.id,
                answer: reply,
                isActive: this.isActive
            }).then(response => {}).catch(e => {
                this.errors.push(e)
            })
        },
        next: function(){
            console.log(index);
            this.flipped = !this.flipped;
            index = index + 1;
            this.card = this.cards[index];
        },
        post: function (reply) {
            axios.post(url,{
                id: this.card.id,
                answer: reply,
                isActive: this.isActive
            }).then(response => {}).catch(e => {
                this.errors.push(e)
            })
        }

    }
});
