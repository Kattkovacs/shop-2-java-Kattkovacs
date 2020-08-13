import {dataHandler} from "./data-handler.js";

export let dom = {

    init: function () {
        dom.addActionToAddCartButtons();
        dom.addActionToQuantityButtons();
        dom.isEmptyCart();
        dom.addActionToAddCheckoutButton();
        dom.sleepModal();
        dom.addActionToCartLink();
    },

    addActionToAddCartButtons: function() {
        let buttons = document.querySelectorAll(".add-to-cart");

        for (let button of buttons){
            button.addEventListener('click', () => {
                dom.modifyCartSize(parseInt(document.querySelector(".cart-size").innerText) + 1)
                document.querySelector(".cart-size")
                dataHandler.addToCart(
                    dom.showResult,
                    button.getAttribute('product-id'))
            });
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
            let body = document.querySelector("body")
            if (body.contains(alertDiv)) body.removeChild(alertDiv);
        }, 3000)
    },

    addActionToQuantityButtons: function () {
        let quantities = document.querySelectorAll(".product");
        for (let quantity of quantities){
            let productId = quantity.getAttribute("product-id");
            let decrementButton = quantity.querySelector(".minus");
            let incrementButton = quantity.querySelector(".plus");
            let removeButton = quantity.querySelector(".remove-button");
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
            removeButton.addEventListener('click', dataHandler.removeLineItem.bind(
                event,
                dom.modifyCart,
                productId
            ));
        }
    },

    modifyCartSize: function(size){
        let cartSize = document.querySelector(".cart-size")
        cartSize.innerText = size
    },

    modifyCart: function (response) {
        let productDiv = document.querySelector(`[product-id="${response['productId']}"]`);
        dom.modifyTotalPrice(response["totalOrderPrice"]);
        dom.modifyCartSize(response["sizeOfCart"])
        if (response["quantity"] <= 0) {
            let cart = document.querySelector("#cart");
            cart.removeChild(productDiv);
            dom.isEmptyCart()
            return;
        }
        productDiv.querySelector(".quantity-number").innerText = response["quantity"];
        productDiv.querySelector(".product-price").innerText = response["totalProductPrice"];
    },

    modifyTotalPrice: function (price) {
        let totalPrice = document.querySelector(".total-price");
        totalPrice.innerText = price;
    },

    isEmptyCart: function () {
        let cart = document.querySelector("#cart");
        if (cart == null) return false;
        let productsNumber = cart.querySelectorAll(".product").length
        if (productsNumber > 0) return false
        dom.disableCheckoutButton()
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
    },

    disableCheckoutButton: function () {
        let checkout = document.querySelector(".checkout-button");
        checkout.disabled = true;
    },

    addActionToAddCheckoutButton: function() {
        let checkoutButton = document.querySelector(".checkout-button");
        if (checkoutButton == null) return;
        checkoutButton.addEventListener('click', dataHandler.checkout.bind(
            event,
            dom.convertPage,
        ));
    },

    convertPage: function(response) {
        return response.text();
    },

    openUserDataForm: function (data) {
        dom.activateModal();
        let modal = document.querySelector("#modal");
        modal.querySelector(".modal-body").innerHTML = data;
        modal.querySelector(".modal-title").innerText = "Please provide your data.";
        let saveButton = document.querySelector(".save-button");
        $('#modal').modal();
        saveButton.addEventListener('click',
            function eventListener() {
                saveButton.removeEventListener('click', eventListener)
                if (!$('#form')[0].checkValidity()) {
                    console.log("Not valid")
                    return
                }
                dom.sleepModal();
                let form = document.getElementById('form');
                let formData = new FormData(form);
                let object = {};
                formData.forEach(function(value, key){
                    object[key] = value;
                });
                dataHandler.saveUserDetail(
                    dom.convertPage,
                    object
                )
            }
        )
    },

    openReviewPage: function(data) {
        dom.activateModal();
        let modal = document.querySelector("#modal");
        modal.querySelector(".modal-body").innerHTML = data;
        modal.querySelector(".modal-title").innerText = "Please review your order details";
        let saveButton = document.querySelector(".save-button");
        saveButton.innerText = "Go To Payment";
        saveButton.addEventListener('click',
            function eventListener() {
                saveButton.removeEventListener('click', eventListener)
                dom.sleepModal();
                dataHandler.goToPayment(
                    dom.convertPage,
                )
            }
        )
    },

    sleepModal: function () {
        let modal = document.querySelector("#modal");
        if (modal == null) return
        let saveButton = modal.querySelector(".save-button");
        let spinner = modal.querySelector(".spinner-border");
        spinner.style.visibility = "visible";
        saveButton.disabled = true;
    },

    activateModal: function () {
        let modal = document.querySelector("#modal");
        let saveButton = modal.querySelector(".save-button");
        let spinner = modal.querySelector(".spinner-border");
        spinner.style.visibility = "hidden";
        saveButton.disabled = false;
    },

    openPaymentPage: function(data) {
        dom.activateModal();
        let modal = document.querySelector("#modal");
        modal.querySelector(".modal-body").innerHTML = data;
        modal.querySelector(".modal-title").innerText = "Please give your payment details: ";
        let saveButton = document.querySelector(".save-button");
        saveButton.innerText = "Confirm & Pay";
        saveButton.addEventListener('click',
            function eventListener() {
                saveButton.removeEventListener('click', eventListener)
                dom.sleepModal();
                let form = document.getElementById('form');
                let formData = new FormData(form);
                let object = {};
                formData.forEach(function(value, key){
                    object[key] = value;
                });
                dataHandler.pay(
                    dom.convertPage,
                    object
                )
            }
        )
    },

    openResultPage: function(data) {
        dom.activateModal();
        let modal = document.querySelector("#modal");
        modal.querySelector(".modal-body").innerHTML = data;
        modal.querySelector(".modal-title").innerText = "Payment details";
        let saveButton = document.querySelector(".save-button");
        saveButton.innerText = "Ok";
        saveButton.addEventListener('click',
            function eventListener() {
                saveButton.removeEventListener('click', eventListener)
                dom.sleepModal();
                $('#modal').modal("toggle");
                window.location.replace("/");
            }
        )
    },
    addActionToCartLink: function() {
        let link = document.querySelector(".cart-list");
        let shape = document.querySelector(".shape")
        link.addEventListener('mouseover', () => {
            shape.classList.add("animate");
        })
        link.addEventListener('mouseleave', () => {
            shape.classList.remove("animate");
        })

    }
}
