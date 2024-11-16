#!/bin/bash

# Must be run from root directory i.e. ./scripts/run.sh

lib="lib/*" # Any used libraries goes in lib directory

outDir="java_out" # stores .class files

javaFiles=$(find "src" -name "*.java") # All .java files in src directory

# Compile to class files, save in outDir
# https://inside.java/2024/06/18/quality-heads-up/
javac --enable-preview -source 23  \
  -proc:full \
  -d $outDir \
  -cp $lib \
  $javaFiles

# Run the main class
java --enable-preview \
  -ea \
  -cp $lib:$outDir \
  MoneyMain $@

rm -rf $outDir