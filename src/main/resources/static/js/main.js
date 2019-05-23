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
        sessionDTO: [],
        error: false,
        flipped: false,
        errors: [],
        isActive: true
    },
    computed:{
        card: function () {
            console.log(this.flipped);
            console.log(this.sessionDTO);
            return this.sessionDTO[index];
        }
    },
    created() {
        axios.get(url).then(response => {
            this.sessionDTO = response.data
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
            url = "/rest/session/" + document.querySelector("[name~=pack][content]").content;
            axios.put(url,{
                isActive: this.sessionDTO.active,
                session_id: this.sessionDTO.session_id,
                pack_id: this.sessionDTO.pack_id,
                card_id: this.sessionDTO.card_id,
            }).then(response => {}).catch(e => {
                this.errors.push(e)
            })
        },
        getNewCard: function(){
            axios.get(url).then(response => {
                this.sessionDTO = response.data
            });
        },
        next: function(){
            console.log(index);
            this.flipped = !this.flipped;
            this.getNewCard();

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
