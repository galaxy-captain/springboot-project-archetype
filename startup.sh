#!/bin/bash

DIR=./output

mkdir -p ${DIR}

mvn --settings ./settings.xml clean package -U -Dmaven.test.skip=true
mvn clean

ACTIVE_ENV=dev
SERVER_PORT=8080

java -Dspring.profiles.active=${ACTIVE_ENV} -Dserver.port=${SERVER_PORT} -jar ${DIR}/project.jar