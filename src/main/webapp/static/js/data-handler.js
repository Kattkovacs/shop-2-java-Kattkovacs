import { dom } from "./dom.js";
export let dataHandler = {


    _data: {},

    _api_get: function (url, callback) {
        fetch(url, {
            method: 'GET',
            credentials: 'same-origin'
        })
            .then(response => response.json())
            .then(json_response => callback(json_response));
    },

    _get: function (url, callback) {
        fetch(url, {
            method: 'GET',
            credentials: 'same-origin'
        })
            .then(response => callback(response));
    },

    _post: function (url, data, callback) {
        fetch(url, {
            method: 'POST',
            credentials: "include",
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(data)

        })
            .then(response => callback(response))
    },

    _api_post: function (url, data, callback) {
        fetch(url, {
            method: 'POST',
            credentials: "include",
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(data)

        })
            .then(response => response.json())
            .then(json_response => callback(json_response))
    },

    init: function () {
    },

    addToCart: function (callback, productId) {
        let url = `/cart?add=${productId}`
        dataHandler._get(url, (response) => {
            dataHandler._data = response;
            callback(response)
        });
    },

    decrementQuantity: function (callback, productId, event) {
        let url = `/cart?decrement=${productId}`
        dataHandler._api_get(url, (response) => {
            dataHandler._data = response;
            callback(response)
        });
    },

    incrementQuantity: function (callback, productId, event) {
        let url = `/cart?increment=${productId}`
        dataHandler._api_get(url, (response) => {
            dataHandler._data = response;
            callback(response)
        });
    },

    removeLineItem: function (callback, productId, event) {
        let url = `/cart?remove=${productId}`
        dataHandler._api_get(url, (response) => {
            dataHandler._data = response;
            callback(response)
        });
    },

    checkout: function (callback, event) {
        let url = `/checkout`
        dataHandler._get(url, (response) => {
            dataHandler._data = response;
            callback(response).then(
                function(data) {
                    dom.openUserDataForm(data)
                }).catch(function(error){
                    alert(error);
                }
            )
        });
    },


    saveUserDetail: function (callback, formData) {
        let url = `/checkout`
        dataHandler._post(url, formData, (response) => {
            dataHandler._data = response;
            callback(response).then(
                function(data) {
                    dom.openReviewPage(data)
                }).catch(function(error){
                    alert(error);
                }
            )
        });
    },

    goToPayment: function (callback) {
        let url = `/payment`
        dataHandler._get(url, (response) => {
            dataHandler._data = response;
            callback(response).then(
                function(data) {
                    dom.openPaymentPage(data)
                }).catch(function(error){
                    alert(error);
                }
            )
        });
    },

    pay: function (callback, formData) {
        let url = `/payment`;
        dataHandler._post(url, formData, (response) => {
            dataHandler._data = response;
            callback(response).then(
                function(data) {
                    dom.openResultPage(data, "Payment details")
                }).catch(function(error){
                    alert(error);
                }
            )
        });
    },

    sendRegistration: function (callback, formData) {
        let url = `/registration`;
        dataHandler._post(url, formData, (response) => {
            dataHandler._data = response;
            callback(response).then(
                function(data) {
                    dom.openResultPage(data, "Registration details")
                }).catch(function(error){
                    alert(error);
                }
            )
        });
    },



    registration: function (callback) {
        let url = `/registration`;
        dataHandler._get(url, (response) => {
            dataHandler._data = response;
            callback(response).then(
                function(data) {
                    dom.openRegistrationPage(data)
                }).catch(function(error){
                    alert(error);
                }
            )
        });
    },

    login: function (callback) {
        let url = `/login`;
        dataHandler._get(url, (response) => {
            dataHandler._data = response;
            callback(response).then(
                function(data) {
                    dom.openLoginPage(data)
                }).catch(function(error){
                    alert(error);
                }
            )
        });
    },
    sendLogin: function (callback, formData) {
        let url = `/login`;
        dataHandler._post(url, formData, (response) => {
            dataHandler._data = response;
            callback(response).then(
                function(data) {
                    dom.openResultPage(data, "Login details")
                }).catch(function(error){
                    alert(error);
                }
            )
        });
    },
}