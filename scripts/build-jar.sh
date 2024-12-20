#!/bin/bash

# Must be run from root directory i.e. ./scripts/build-native.sh

lib="lib/*" # Any used libraries goes in lib directory

outDir="earth_java_out" # stores .class files
rm -rf $outDir

jar_name="earth" # jar file name

javaFiles=$(find "src" -name "*.java") # All .java files in src directory

# Compile to class files, save in outDir
javac --enable-preview -source 23 \
  -d $outDir \
  -cp $lib \
  $javaFiles

cp -r lib/ "$outDir/lib"
# get all the libraries seperated by space
libs=$(find "$outDir/lib" -name "*.jar" | tr '\n' ' ' | sed 's/ / /g')
# remove $java_out prefix
libs=$(echo $libs | sed "s/$outDir\///g")

manifest=$(cat <<EOF
Main-Class: earth.EarthMain
Class-Path: $libs
EOF
)

jar \
  --create \
  --file $jar_name.jar \
  --manifest <(echo "$manifest") \
  -C $outDir .

rm -rf $outDir