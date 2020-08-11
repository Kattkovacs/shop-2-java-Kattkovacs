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
        dataHandler._get(url, (response) => {
            dataHandler._data = response;
            callback(response, productId)
        });
    },

    incrementQuantity: function (callback, productId, event) {
        let url = `/cart?increment=${productId}`
        dataHandler._get(url, (response) => {
            dataHandler._data = response;
            callback(response, productId)
        });
    }
}