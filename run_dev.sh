#!/bin/bash

./gradlew assemble
docker compose build --no-cache -t backend
docker compose up
docker compose stop
docker compose rm -fsv backend
docker rmi backend-backend