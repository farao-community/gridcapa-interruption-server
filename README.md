[![Actions Status](https://github.com/farao-community/gridcapa-interruption-server/workflows/CI/badge.svg)](https://github.com/farao-community/gridcapa-interruption-server/actions)
[![Coverage Status](https://sonarcloud.io/api/project_badges/measure?project=farao-community_gridcapa-interruption-server&metric=coverage)](https://sonarcloud.io/component_measures?id=farao-community_gridcapa-interruption-server&metric=coverage)
[![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=farao-community_gridcapa-interruption-server&metric=alert_status)](https://sonarcloud.io/dashboard?id=farao-community_gridcapa-interruption-server)
[![MPL-2.0 License](https://img.shields.io/badge/license-MPL_2.0-blue.svg)](https://www.mozilla.org/en-US/MPL/2.0/)
[![Join the community on Spectrum](https://withspectrum.github.io/badge/badge.svg)](https://spectrum.chat/farao-community)
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
