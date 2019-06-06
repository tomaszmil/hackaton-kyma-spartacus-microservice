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


        var orderId = event.data.orderCode;
        var baseSiteId = `${process.env.BASE_STORE_ID}`;
        //var getOrderUrl = apiUrl + "/" + baseSiteId + "/orders/" + event.data.orderCode
        var getOrderUrl = `${process.env["GATEWAY_URL"]}/electronics/orders/${orderId}`
        var traceCtxHeaders = extractTraceHeaders(event.extensions.request.headers)
        request.get({headers: traceCtxHeaders, url: getOrderUrl, json: true}, function (error, response, body) {
            if (error === null) {
                if (response.statusCode == '200') {
                    var addOrderUrl = `${process.env.MICROSERVICE_URL}/add/order`;
                    var userId = body.user.uid;
                    var products = [];

                    body.entries.forEach(function (element) {
                        products.push(element.product);
                    });
                    products.forEach(function (product) {
                        var requestData = {
                            "userdId": userId,
                            "productCode": product.code,
                            "productName": product.name
                        };

                        request.post({
                            headers: traceCtxHeaders,
                            url: addOrderUrl,
                            json: requestData
                        }, function (error, response, body) {
                            console.log("Add order request sent with following data: ", requestData);
                        });
                    });
                } else {
                    console.log('Call to EC webservice failed with status code ' + response.statusCode);
                    console.log(response.body);
                }
            } else {
                console.log(error);
            }
        });
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