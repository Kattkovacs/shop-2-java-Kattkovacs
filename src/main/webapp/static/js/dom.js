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
                dom.showResult,
                button.getAttribute('product-id')));
        }
    },

    showResult: function(response) {
        dom.showAlertPopup("Item added to chart successfully");
    },

    showAlertPopup: function(message) {
        let alertDiv = document.createElement("div");
        alertDiv.classList.add("alert", "alert-success", "fixed-top", "text-center");
        alertDiv.setAttribute("id", "success-alert");
        let dismissButton = document.createElement("button");
        dismissButton.classList.add("close");
        dismissButton.setAttribute("type", "button");
        dismissButton.setAttribute("data-dismiss", "alert");
        dismissButton.innerText="x";
        let result = document.createElement("strong");
        result.innerText = "Success!"
        let alertText = document.createElement("div");
        alertText.classList.add("alert-text");
        alertText.innerText = message;
        alertDiv.appendChild(dismissButton);
        alertDiv.appendChild(result);
        alertDiv.appendChild(alertText);
        document.querySelector("body").appendChild(alertDiv);
        setTimeout(() => {
            document.querySelector("body").removeChild(alertDiv)
        }, 3000)
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
















