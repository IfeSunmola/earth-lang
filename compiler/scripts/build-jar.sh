#!/bin/bash

# Must be run from root directory i.e. ./scripts/build-jar.sh

./mvnw clean package
cp ./target/earth-0.0.1.jar ./earth.jar