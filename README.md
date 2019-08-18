# ty-ideapp

API (back) : ty-ideapp-api + ty-ideapp-dto  
Front : ty-ideapp-react-front  
BDD (Behavior Driven Development - Integration Tests) : ty-ideapp-cucumber  

0. JUnit naming convention

UnitOfWork_StateUnderTest_ExpectedBehavior

1. Install API

```sh
docker run --name ty-mariadb -e MYSQL_ROOT_PASSWORD=root -d ideapp:latest

docker build --tag=ty-ideapp-api .
docker build --file Dockerfile-buildNdeploy --tag=ty-ideapp-api .
#docker image ls
docker run -d -p 8100:8100 ty-ideapp-api
#docker container ls
#docker container stop <Container NAME or ID>
```

2. Install Front

```sh
docker build --tag=ty-ideapp-react-front .
#docker image ls
docker run -d -p 3000:5000 ty-ideapp-react-front
#docker container ls
#docker container stop <Container NAME or ID>
```

3. All in one

```sh
docker swarm init
docker stack deploy -c docker-compose.yml ideapp
docker service ls
#docker stack rm ideapp

#docker exec -it <Container ID> /bin/sh
#command winpty docker exec -it <Container ID> bash
```
