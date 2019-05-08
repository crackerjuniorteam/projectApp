// const cards = [
//     {
//         question: 'Question',
//         answer: 'Answer',
//         flipped: false,
//     },
//     {
//         question: 'Question2',
//         answer: 'Answer2',
//         flipped: false,
//
//     },
//     {
//         question: 'Question3',
//         answer: 'Answer3',
//         flipped: false,
//     },
//     {
//         question: 'Question4',
//         answer: 'Answer4',
//         flipped: false,
//     },
// ];
const url = "/rest/session/test";
let index = 0;

const vm = new Vue({
    el: '#flashcard-app',
    data: {
        cards: [],
        newFront: '',
        newBack: '',
        error: false,
        flipped: false
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
        addNew: function(){
            if(!this.newFront.length || !this.newBack.length){
                this.error = true;
            } else {
                this.cards.push({
                    front: this.newFront,
                    back: this.newBack,
                    flipped: false
                });
                this.newFront = '';
                this.newBack = '';
                this.error = false;
            }
        },
        next: function(){
            if (index > this.cards.length) {
                alert("Карты кончились")
            }
            this.flipped = !this.flipped;
            index = index + 1;
            this.card = this.cards[index];

        }
    }
});