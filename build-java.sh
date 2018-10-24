#!/bin/bash
mkdir output 2>/dev/null

javac \
   -encoding windows-1252 \
   -d Jabberpoint/bin \
   Jabberpoint/src/*.java

jar cf output/Jabberpoint.jar \
    Jabberpoint/bin/*.class

echo "Jabberpoint built to output/Jabberpoint.jar"

