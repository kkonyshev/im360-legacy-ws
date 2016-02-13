### API

Only two methods implemented now:

* ```/options/{restaurantID}/{active}```
* ```/bags/{restaurantID}/{status}/{ATTR1}```

# Running application

Next command will run application on ```localhost:8080```

```sh
$  mvn spring-boot:run
```

### API call examples

Following calls will return ```resultCode=success```

* http://localhost:8080/options/1/0
* http://localhost:8080/bags/1/0/LAB
* http://localhost:8080/bags/1/0/PHOTO

Following calls will return ```resultCode=fail```

* http://localhost:8080/options/1/-1
* http://localhost:8080/bags/1/9/PHOTO

### Using [Docker] [Dsi]

Building project and docker image:

```sh
$ mvn clean package docker:build
```

Then you can run created docker container:

```sh
$ docker run -p 8080:8080 kkonyshev/im360-legacy-ws
```

Also it is possible to pull image from [dockerhub] [Dh]:

```sh
$ docker pull kkonyshev/im360-legacy-ws
```

### Integration tests

Integration tests run on every build. 
To skip integration test add ```-DskipTests=true```

```sh
$ mvn clean package -DskipTests=true
```

Command for run integration tests only without packaging: 

```sh
$ mvn clean test
```

### Configuration

You can change datasrouce and application ports in configuration file:
* database [src/main/resources/application.properties] [DS]

For configuring docker image building see:
* docker [src/main/docker/Dockerfile] [DOCKER]


   [DS]: <https://github.com/kkonyshev/im360-legacy-ws/tree/master/src/main/resources/application.properties>
   [DOCKER]:  <https://github.com/kkonyshev/im360-legacy-ws/tree/master/src/main/docker/Dockerfile>
   [Dsi]: <https://www.docker.com>
   [Dh]: <https://hub.docker.com/r/kkonyshev/im360-legacy-ws/>

