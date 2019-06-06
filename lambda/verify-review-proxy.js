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

        if (event.extensions.request.method === "POST") {
            var destinationUrl = `http://purchaseswebservice.${process.env.USER_ENVIRONMENT}:8017/verify/review`;
            return await
            sendRequest(destinationUrl, event.data);
        }
    }
}

async

function sendRequest(destinationUrl, data) {
    return new Promise(function (resolve, reject) {
        request.post({url: destinationUrl, json: data}, function (error, response, body) {
            console.log(body);
            console.log(error);
            resolve(body);
        });
    });
}
