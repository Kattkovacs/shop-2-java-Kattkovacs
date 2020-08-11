import {dataHandler} from "./data-handler.js";

export let dom = {

    init: function () {
        dom.addActionToAddCartButtons();
        dom.addActionToQuantityButtons();
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
    },

    addActionToQuantityButtons: function () {
        let quantities = document.querySelectorAll(".quantity");
        for (let quantity of quantities){
            let productId = quantity.getAttribute("product-id");
            let decrementButton = quantity.querySelector(".minus");
            let incrementButton = quantity.querySelector(".plus");
            decrementButton.addEventListener('click', dataHandler.decrementQuantity.bind(
                event,
                dom.decrementQuantity,
                productId
            ));
            incrementButton.addEventListener('click', dataHandler.incrementQuantity.bind(
                event,
                dom.incrementQuantity,
                productId
            ));
        }
    },

    decrementQuantity: function (response, productId) {
        let quantity = document.querySelector(`[product-id="${productId}"]`);
        let number = quantity.querySelector(".quantity-number");
        number.innerText = parseInt(number.innerText) - 1;
    },

    incrementQuantity: function (response, productId) {
        let quantity = document.querySelector(`[product-id="${productId}"]`);
        let number = quantity.querySelector(".quantity-number");
        number.innerText = parseInt(number.innerText) + 1;
    }
}
















