const cards = [
    {
        question: 'Question',
        answer: 'Answer',
        flipped: false,
    },
    {
        question: 'Question2',
        answer: 'Answer2',
        flipped: false,

    },
    {
        question: 'Question3',
        answer: 'Answer3',
        flipped: false,
    },
    {
        question: 'Question4',
        answer: 'Answer4',
        flipped: false,
    },
];
const url = "/rest/session/test";
var index = 0;

const vm = new Vue({
    el: '#flashcard-app',
    data: {
        card : cards[index],
        cards: cards,
        newFront: '',
        newBack: '',
        error: false
    },

    mounted() {
        axios.get(url).then(response => {

            this.cards = response.data

        });
        this.card = this.cards[0];
    },

    methods: {
        toggleCard: function(card){
            card.flipped = !card.flipped;
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
            if (index > cards.length) {
                alert("Карты кончились")
            }
            index = index + 1;
            this.card = cards[index];
        }
    }
});