import {dataHandler} from "./data-handler.js";

export let dom = {

    init: function () {
        dom.addActionToAddCartButtons()
    },

    addActionToAddCartButtons: function() {
        let buttons = document.querySelectorAll(".add-to-cart");

        for (let button of buttons){
            button.addEventListener('click', dataHandler.addToCart.bind(
                event,
                dom.showResultPopUp,
                button.getAttribute('product-id')));
        }
    },

    showResultPopUp: function(response) {
        alert("Test");
    }

}
