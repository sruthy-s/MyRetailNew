#!/bin/bash

case "$1" in
  start-lite)
    mvn clean install
    java -jar target/myretail-*.jar
  ;;
  start)
    docker-compose up --build
  ;;
esac