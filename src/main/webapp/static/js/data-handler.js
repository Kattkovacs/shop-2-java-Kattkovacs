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

    addToCart: function (callback, productId, event) {
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
        let url = `/userform`
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
        let url = `/userform`
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
}