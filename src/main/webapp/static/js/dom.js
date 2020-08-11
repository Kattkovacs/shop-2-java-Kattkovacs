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
        let quantities = document.querySelectorAll(".product");
        for (let quantity of quantities){
            let productId = quantity.getAttribute("product-id");
            let decrementButton = quantity.querySelector(".minus");
            let incrementButton = quantity.querySelector(".plus");
            decrementButton.addEventListener('click', dataHandler.decrementQuantity.bind(
                event,
                dom.modifyCart,
                productId
            ));
            incrementButton.addEventListener('click', dataHandler.incrementQuantity.bind(
                event,
                dom.modifyCart,
                productId
            ));
        }
    },

    modifyCart: function (response) {
        let productDiv = document.querySelector(`[product-id="${response['productId']}"]`);
        console.log(response)
        if (response["quantity"] <= 0) {
            let cart = document.querySelector("#cart");
            cart.removeChild(productDiv);
            return;
        }
        productDiv.querySelector(".quantity-number").innerText = response["quantity"];
        productDiv.querySelector(".product-price").innerText = response["totalProductPrice"]
    },
}
















