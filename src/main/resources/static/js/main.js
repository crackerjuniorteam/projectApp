const cards = [
    {
        question: 'Question',
        answer: 'Answer',
        flipped: false,
    },
    {
        question: 'Invented the "Clarke Calculator"',
        answer: 'Edith Clarke',
        flipped: false,

    },
    {
        question: 'Famous World War II Enigma code breaker',
        answer: 'Alan Turing',
        flipped: false,
    },
    {
        question: 'Created satellite orbit analyzation software for NASA',
        answer: 'Dr. Evelyn Boyd Granville',
        flipped: false,
    },
];
const url = "/rest/session/test";


const vm = new Vue({
    el: '#flashcard-app',
    data: {
        card : cards[0],
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
        }
    }
});