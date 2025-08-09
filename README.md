# The earth language

A toy compiler that generates JVM bytecode

## Table of contents

- [Downloading from the releases page](#downloading-from-the-releases-page)
- [Using the (Docker) playground](#using-the-docker-playground)
- [Run each part of the project individually](#run-each-part-of-the-project-individually)

## Downloading from the releases page

1. Download the corresponding release for your platform from the releases page
   at: https://github.com/IfeSunmola/earth-lang/releases
2. Extract the archive
3. cd into the extracted directory and run a sample code:
    ```bash
    ./earth samples/fizz_buzz.earth
    ``` 

## Using the (Docker) playground

I'm not saying you should be skeptical, but do you really wanna run a binary
built by some random person on your PC? No? Good.

This is what the playground looks like:

![Playground](./playground-image.png)

And you can run it with:

```shell
# There's really no excuse to not have curl on your system
# First download the docker compose file
curl -o \
  earth-docker-compose.yaml \
  https://raw.githubusercontent.com/IfeSunmola/earth-lang/refs/heads/main/docker-compose-remote.yaml

# And run it
docker compose \
  -p earth-playground \
  -f earth-docker-compose.yaml \
  up 
```

The server will be available at `http://localhost:8080`, and the frontend
(playground) will be available at `http://localhost:5173`.

## Run each part of the project individually

The two steps described above would be the easiest way to get started, but
if you want to do it from scratch, steps for running each part of the
project are located in the README.md in each directory:

1. [Compiler](compiler) - Contains the actual compiler and runtime
2. [web-client](web-client) - Contains the frontend for the playground
3. [web-server](web-server) - Contains the backend for the playground