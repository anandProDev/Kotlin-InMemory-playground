# Vanakkam

A simple kotlin app to play with in-memory databases

- [x] A simple rest controller 
- [x] Talk with Redis-DB and return a json
- [x] Create a docker file
- [x] Create a docker compose file
-----------
- [x] Kafka
    - [x] writer
    - [x] reader    
- [ ] Feature to switch DBs on the fly
------------
- [ ] Test container
- [ ] Micrometer
- [ ] Swagger
- [ ] Grafana
-----------------
## Setup

##### Start redis as standalone

```docker run --name some-redis -p 6379:6379 -d redis redis-server --appendonly yes```
 
 
#####  To build image in docker for the kotlin redis app
 
 ``` ./gradlew docker build ```
 
 ``` docker build -t anandindustries/kotlinredis:latest . ```
 
 ##### To run the image as a stand-alone
 
  ```docker run -p 8080:8080 -t com.anand.industries/kotlinredis```
  
 ##### To run both redis and kotlin app together, you would have to set up a network connection in docker
 
 ```
docker network create -d bridge mybridge
docker run --rm --network=mybridge -p=6379:6379 --name=redis redis:3-alpine
docker run --rm --network=mybridge -it -p=8080:8080 --name=kotlinredisapp com.anand.industries/kotlinredis ``` 

