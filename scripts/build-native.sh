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

# Need to build jar first
./scripts/build-jar.sh

# Build native image
native-image --enable-preview \
  -jar earth.jar \
  -o earth \
  -march=compatibility \
  -H:Class=earth.EarthMain