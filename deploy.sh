#!/bin/sh
git pull
mvn clean install
cp -r src/main/resources /opt/applications/businessmanager/
cp target/businessmanager-1.0.1.jar /opt/applications/businessmanager/