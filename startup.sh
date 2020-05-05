#!/bin/bash

DIR=./output
LOG=./log

mkdir -p ${DIR}
mkdir -p ${LOG}

mvn --settings ./settings.xml clean package -U -Dmaven.test.skip=true

ACTIVE_ENV=prd
SERVER_PORT=8000

JVM_OPTS=" -server -XX:+UseG1GC -Xms256m -Xmx256m -Xss1024K -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=256m "

java ${JVM_OPTS} -Dspring.profiles.active=${ACTIVE_ENV} -Dserver.port=${SERVER_PORT} -jar ${DIR}/project.jar