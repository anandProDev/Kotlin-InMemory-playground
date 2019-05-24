# Vanakkam

A simple kotlin app to play with in-memory databases

- [x] A simple rest controller 
- [x] Talk with Redis-DB and return a json [x]
- [ ] Create a docker file
- [ ] Create a docker compose file
-----------
- [ ] Kafka
    - [ ] writer
    - [ ] reader    
- [ ] Feature to switch DBs on the fly
------------
- [ ] Test container
- [ ] Micrometer
- [ ] Swagger
- [ ] Grafana
-----------------
## Setup

Start redis

```docker run --name some-redis -p 6379:6379 -d redis redis-server --appendonly yes``` 