# Service Discovery
[![Build and test](https://github.com/groot-mg/service-discovery/actions/workflows/service-discovery-ci.yml/badge.svg)](https://github.com/groot-mg/service-discovery/actions/workflows/service-discovery-ci.yml) [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=groot-mg_service-discovery&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=groot-mg_service-discovery) [![License: MIT](https://img.shields.io/badge/License-MIT-green.svg)](https://github.com/groot-mg/service-discovery/blob/main/LICENSE)

The purpose of a service discovery is load balancing and failover of middle-tier servers. It plays a critical role a mid-tier infra.

Each service will self register in the service discovery and the API Gateway responsible for receiving the user requests, will forward the requests to the registered services in the service discovery.

## How to run

Service discovery should run together with the API Gateway and other services, to run this project, please see [docker-local-setup](https://github.com/groot-mg/docker-local-setup).