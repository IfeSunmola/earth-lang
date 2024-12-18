#!/bin/bash

# Must be run from root directory i.e. ./scripts/build-native.sh
# GraalVM that supports java 23 must be used

outDir="java_out" # stores .class files

bin="earth" # native image name

javaFiles=$(find "src" -name "*.java") # All .java files in src directory

# Compile to class files, save in outDir
javac --enable-preview -source 23 \
  -d $outDir \
  $javaFiles

# Use .class files to create native image
native-image --enable-preview \
  -cp $outDir \
  -o $bin \
  -H:Class=EarthMain

rm -rf $outDir