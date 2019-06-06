// dependency
var dependency = {
    "name": "app",
    "version": "0.0.1",
    "dependencies": {
        "request": "^2.85.0"
    }
}

// lambda
const request = require('request');

module.exports = {
    main: async function(event, context) {
        if (event.extensions.request.method === "GET") {
            var userId = event.data.userId
            var destinationUrl = `http://purchaseswebservice.${process.env.USER_ENVIRONMENT}:8017/${userId}/reviews`;
            return await
            sendRequest(destinationUrl, event.data);
        }
    }
}

async

function sendRequest(destinationUrl, data) {
    return new Promise(function (resolve, reject) {
        request.get({url: destinationUrl, json: data}, function (error, response, body) {
            resolve(body);
        });
    });
}
