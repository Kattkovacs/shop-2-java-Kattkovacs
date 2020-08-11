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
        this._api_get(url, (response) => {
            this._data = response;
            callback(response)
        });
    }
}