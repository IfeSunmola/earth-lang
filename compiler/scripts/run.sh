#!/bin/bash

# Must be run from root directory i.e. ./scripts/run.sh

./mvnw -q compile exec:java -Dexec.args="$*"