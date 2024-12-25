#!/bin/bash

# Must be run from root directory i.e. ./scripts/build-native.sh

# Build a FAT jar with all dependencies. It's really just one dependency but eh.
# Ya I could use maven or gradle but i'd rather not get tied down in
# src/main/java and all that

lib="lib/*" # Any used libraries goes here
jar_name="earth.jar"

tmp_dir="tmp"
rm -rf $tmp_dir
mkdir -p $tmp_dir

# First, extract all dependencies into tmp_dir
cd $tmp_dir
for f in "../$lib"; do
  jar xf $f
done
cd ..

# Second, compile our code to .class files
javac --enable-preview -source 23 \
  -d $tmp_dir \
  -cp $tmp_dir \
  $(find "src" -name "*.java")

# Third, create the fat JAR
# cfe -> CREATE jar FILE with ENTRY POINT
jar cfe $jar_name earth.EarthMain -C $tmp_dir .

# Clean up
rm -rf $tmp_dir