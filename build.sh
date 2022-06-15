#!/bin/sh

PROJECT_ROOT=`pwd`

javac src/main/java/*.java
cd src/main/java
jar -cvmf ${PROJECT_ROOT}/src/main/resources/META-INF/MANIFEST.MF ${PROJECT_ROOT}/out/csvtojpa.jar *.class
rm *.class
echo "build success"
