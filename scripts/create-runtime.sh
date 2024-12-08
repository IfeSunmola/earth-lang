#!/bin/bash

# Must be run from root directory i.e. ./scripts/create-runtime.sh
# Uses jdeps and jlink to create a custom java runtime consisting only of the
# required modules used by the compiler

# Only java.base for now. Later, use `jdeps --print-module-deps Output.class`
# to get all the required modules. Where Output.class is a .class file that
# contains code that uses all the required modules.
modules=("java.base")

# Create a custom runtime image
jlink --add-modules "${modules[@]}" --output java-runtime