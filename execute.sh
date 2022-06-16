#!/bin/sh

PROJECT_ROOT=`pwd`

cd out
java -jar csvtojpa.jar
echo "check out/dist"

