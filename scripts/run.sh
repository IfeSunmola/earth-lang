#!/bin/bash

# Must be run from root directory i.e. ./scripts/run.sh

outDir="java_out" # stores .class files

javaFiles=$(find "src" -name "*.java") # All .java files in src directory

# Compile to class files, save in outDir
# https://inside.java/2024/06/18/quality-heads-up/
javac --enable-preview -source 23  \
  -d $outDir \
  $javaFiles

# Run the main class
java --enable-preview \
  -cp $outDir \
  EarthMain $@

rm -rf $outDir