# gridcapa-interruption-server

This repository contains the interruption server for GridCapa. It is dedicated to containing interrupted task runs information.

## Build application

Application is using Maven as base build framework. Application is simply built with following command.

```bash
mvn install
```

## Build docker image

For building Docker image of the application, start by building application.

```bash
mvn install
```

Then build docker image

```bash
docker build -t farao/gridcapa-interruption-server .
```
