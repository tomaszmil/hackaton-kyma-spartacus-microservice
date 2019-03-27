Simple Purchases CRUD Service
-------------------

# API
* https://editor.swagger.io/?url=https://raw.githubusercontent.com/kenlomaxhybris/purchasesWebService/master/docs/api/api.yaml
* Based on https://spring.io/guides/gs/accessing-data-rest/

# To build
mvn clean package

# Run locally
java -Dserver.port=8017 -jar target/purchasesWebService-0.1.0.jar

# invoke
## Get: 
curl http://localhost:8017/purchases
## Post: 
curl -i -X POST -H "Content-Type:application/json" -d '{"nameSpace": "space1", "postalCode": "BN44", "town": "Brighton", "total": "1234"}' http://localhost:8017/purchases
curl -i -X POST -H "Content-Type:application/json" -d '{"nameSpace": "space1", "postalCode": "RG11", "town": "Reading", "total": "1234"}' http://localhost:8017/purchases
## Update: 
curl -X PATCH -H "Content-Type:application/json" -d '{"town": "U"}' http://localhost:8017/purchases/1
# Search: 
curl http://localhost:8017/purchases/search/findByNameSpace?nameSpace=SomeNameSpace
# Delete: 
curl -X DELETE http://localhost:8017/purchases/1

# To Docker
mvn clean package
docker build -t kenlomax/purchaseswebservice .
docker run -p 8017:8017 kenlomax/purchaseswebservice:latest
test: curl http://localhost:8017/purchases
docker push kenlomax/purchaseswebservice

# To minikube
minikube start
kubectl config use-context minikube
kubectl delete -f deployment/deployment.yaml
kubectl apply -f deployment/deployment.yaml
minikube dashboard

# To Kubernetes

kubectl apply -f deployment/deployment.yaml -n cl20190327b

https://xfcl20190322a.elastic-bose.cluster.extend.sap.cx/api.yaml
https://editor.swagger.io?url=https://raw.githubusercontent.com/kenlomaxhybris/purchasesWebService/master/docs/api/api.yaml
https://xfcl20190322a.elastic-bose.cluster.extend.sap.cx/purchases


curl -H "Content-Type: application/json" -d '{"orderId":"11854638GU110615ELIN54ZQ","nameSpace": "space1","total": 1234.56,"postalCode": "BN44 3PT","town": "Brighton"}'  https://purchaseswebservice.elastic-bose.cluster.extend.sap.cx/purchases

curl https://purchaseswebservice.elastic-bose.cluster.extend.sap.cx/purchases
curl -X DELETE https://xfcl20190322a.elastic-bose.cluster.extend.sap.cx/purchases/1
