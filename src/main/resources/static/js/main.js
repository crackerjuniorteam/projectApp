let token = document.querySelector("[name~=_csrf][content]").content;

axios.defaults.headers.common = {
    'X-Requested-With': 'XMLHttpRequest',
    'X-CSRF-TOKEN': token,
};
let url = "/rest/session/" + document.querySelector("[name~=pack][content]").content;
//const url = "/rest/session/test";
let index = 0;

const vm = new Vue({
    el: '#flashcard-app',
    data: {
        cards: [],
        newFront: '',
        newBack: '',
        End: false,
        flipped: false,
        postBody: '',
        errors: [],
        isActive: true
    },
    computed:{
        card: function () {
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
            this.next();
            this.post(1);
        },
        saveDoubt: function () {
            this.next();
            this.post(2);
        },
        saveDontRemember: function () {
            this.next();
            this.post(3);
        },
        next: function(){
            console.log(this.isActive);
            if (index > this.cards.length - 2) {
                this.isActive = !this.isActive;
                this.End = !this.End;
                //alert("Карты кончились")
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
                answer: reply,
                isActive: this.isActive
            }).then(response => {}).catch(e => {
                this.errors.push(e)
            })
        }

    }
});