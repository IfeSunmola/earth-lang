#!/bin/bash

# Must be run from root directory i.e. ./scripts/build-native.sh
# GraalVM that supports java 23 must be used

# check if native-image is installed
if ! command -v native-image &> /dev/null
then
    echo "native-image not be found"
    echo "GraalVM 23+ is needed to build  native"
    echo "Download from: https://www.graalvm.org/downloads/"
    exit
fi

lib="lib/*" # Any used libraries goes in lib directory

outDir="java_out" # stores .class files

bin="earth" # native image name

javaFiles=$(find "src" -name "*.java") # All .java files in src directory

# Compile to class files, save in outDir
javac --enable-preview -source 23 \
  -d $outDir \
  -cp $lib \
  $javaFiles

# Use .class files to create native image
native-image --enable-preview \
  -cp $lib:$outDir \
  -o $bin \
  -H:Class=earth.EarthMain

rm -rf $outDir