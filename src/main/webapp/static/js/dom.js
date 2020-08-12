import {dataHandler} from "./data-handler.js";

export let dom = {

    init: function () {
        dom.addActionToAddCartButtons();
        dom.addActionToQuantityButtons();
        dom.isEmptyCart();
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
            dom.isEmptyCart()
            return;
        }
        productDiv.querySelector(".quantity-number").innerText = response["quantity"];
        productDiv.querySelector(".product-price").innerText = response["totalProductPrice"]
    },

    isEmptyCart: function () {
        let cart = document.querySelector("#cart");
        if (cart == null) return false;
        let productsNumber = cart.querySelectorAll(".product").length
        if (productsNumber > 0) return false
        dom.showEmptyCartMessage(cart)
        return true;
    },

    showEmptyCartMessage: function (cart) {
        let empty = document.createElement("div");
        empty.classList.add("card", "p-3", "text-center");
        let text = document.createElement("strong");
        text.innerText = "Your cart is empty!";
        let link = document.createElement("a");
        link.setAttribute("href", "/");
        link.innerText = "Click here to start shopping";
        empty.appendChild(text);
        empty.appendChild(link);
        cart.appendChild(empty);
    }
}
















