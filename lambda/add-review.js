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
const traceHeaders = ['x-request-id', 'x-b3-traceid', 'x-b3-spanid', 'x-b3-parentspanid', 'x-b3-sampled', 'x-b3-Flags', 'x-ot-span-context']

module.exports = {
    main: function (event, context) {
        console.log("Request recived with method:" + event.extensions.request.method);
        if (event.extensions.request.method === "POST") {
            var traceCtxHeaders = extractTraceHeaders(event.extensions.request.headers)
            var destinationUrl = `http://purchaseswebservice.${process.env.USER_ENVIRONMENT}:8017/add/review`;
            var addUserToGroupUrl = `${process.env.GATEWAY_URL}/electronics-spa/customergroups/${process.env.PROMO_GROUP}/members`
            var addUserToGroupData = {
                "members": [
                    {
                        "uid": event.data.userId
                    }
                ]
            }

            console.log("Sending request to: " + destinationUrl);
            request.post({
                headers: traceCtxHeaders,
                url: destinationUrl,
                json: event.data
            }, function (error, response, body) {
                console.log("Send request to micorservice with following data:", event.data);

                if (error === null) {
                    console.log("Request to microservice went well");
                    console.log("Add user to group via url: " + addUserToGroupUrl + " with data:", addUserToGroupData)
                    request.patch({
                        headers: traceCtxHeaders,
                        url: addUserToGroupUrl,
                        json: addUserToGroupData
                    }, function (err, res, bod) {
                        console.log("request sent to api");
                        console.log("error", err);
                        console.log("res", res);
                        console.log("bod", bod);
                        if (err === null) {
                            console.log("User added just fine");
                            console.log("body: ", bod);
                        } else {
                            console.log("Error from commerce api:", err);
                        }
                    });
                } else {
                    console.log("Error from microservice: ", error);
                }
            });
        }
    }
}


// Used to pass the headers through to the next calls, so that tracing will work
function extractTraceHeaders(headers) {
    console.log(headers);
    var map = {};
    for (var i in traceHeaders) {
        h = traceHeaders[i];
        headerVal = headers[h];
        console.log('header' + h + "-" + headerVal);
        if (headerVal !== undefined) {
            console.log('if not undefined header' + h + "-" + headerVal);
            map[h] = headerVal;
        }
    }
    return map;
}