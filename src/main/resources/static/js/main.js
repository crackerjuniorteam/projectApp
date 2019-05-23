let token = document.querySelector("[name~=_csrf][content]").content;

axios.defaults.headers.common = {
    'X-Requested-With': 'XMLHttpRequest',
    'X-CSRF-TOKEN': token,
};

let url = "/rest/session/" + document.querySelector("[name~=pack][content]").content;

const vm = new Vue({
    el: '#flashcard-app',
    data: {
        sessionDTO: [],
        error: false,
        flipped: false,
        errors: [],
        isActive: true,
        cardsCount: 0,
        cardsToRepeat: null,
        needToRepeat: true
    },
    created() {
        axios.get(url).then(response => {
            this.sessionDTO = response.data
        }).catch(e => {
            document.location.href = '/error';
        });
    },
    beforeUpdate() {
        if (this.cardsToRepeat == null) {
            this.cardsToRepeat = this.sessionDTO.cardsToRepeat;
        }
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
            url = "/rest/session/   " + document.querySelector("[name~=pack][content]").content;
            axios.put(url,{
                isActive: this.sessionDTO.active,
                session_id: this.sessionDTO.session_id,
                pack_id: this.sessionDTO.pack_id,
                card_id: this.sessionDTO.card_id,
            }).then(response => {}).catch(e => {
                this.errors.push(e)
            });
            document.location.href = '/'
        },
        getNewCard: function(){
            axios.get(url).then(response => {
                this.sessionDTO = response.data
            }).catch(e => {
                document.location.href = '/error'
            });
        },
        next: function(){
            this.flipped = !this.flipped;
            this.cardsCount += 1;
            this.getNewCard();
            if(this.cardsCount === this.cardsToRepeat){
                alert("You have repeated all the cards planned for today. You can press End Session and take a rest, or continue if you wish.")
                this.needToRepeat = !this.needToRepeat;
            }

        },
        post: function (reply) {
            axios.post(url,{
                isActive: this.sessionDTO.active,
                session_id: this.sessionDTO.session_id,
                pack_id: this.sessionDTO.pack_id,
                card_id: this.sessionDTO.card_id,
                reply: reply
            }).then(response => {}).catch(e => {
                this.errors.push(e)
            })
        }

    }
});
