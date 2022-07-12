# Forum Service

This is a project of a Forum API, to pratice Kotlin with SpringBoot

## How to run local

```shell
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```
If you don't pass the profile config, is used `dev` by default

## How to generate the JAR
```shell
mvn package
```

## Using docker
This project has a Dockerfile, so it's possible use docker to run the application into a container

### Generate the image

```shell
docker build -t forum .
```

### Run container image

```shell
docker run -p 3000:3000 forum
```