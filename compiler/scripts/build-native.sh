#!/bin/bash

# Must be run from root directory i.e. ./scripts/build-native.sh

# check if native-image is installed
if ! command -v native-image &> /dev/null
then
    echo "native-image not be found"
    echo "GraalVM 24+ is needed to build  native"
    echo "Download from: https://www.graalvm.org/downloads/"
    exit
fi

# build jar first
./scripts/build-jar.sh

# build native image
native-image \
  -0b \
  -jar earth.jar \
  -o earth \
  -march=compatibility \
  -H:Class=ifesunmola.earth.EarthMain