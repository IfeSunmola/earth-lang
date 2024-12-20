# The earth language

A toy compiler written in Java and generates JVM bytecode.

Most of the decisions made was based off vibes and feelings so don't ask why
something is the way it is.

## Sample code

See [the samples directory](./samples)

---

## Installation

I didn't want to mess with paths and all that so "installation" simply
downloads the needed files to a directory named `earth-compiler` in the
current working directory. The script downloads everything it needs to work,
so no other setup is needed.

### Linux

```shell
curl  "file:/home/ifesunmola/Documents/dev/java/earth-lang/scripts/install_linux.sh" | bash
```

### Windows

`windows todo`

### Mac

Womp womp. Build from source yourself.

---

## Building from source

### Creating the runtime

JDK 23 is needed to create the runtime. Run:

```shell
./scripts/build-runtime.sh
```

A directory named `earth-jre` will be created in the root of the project.

### Without native binary

Java 23 is needed to use the compiler without compiling to native:

1. Run the jar file in the root of the project (preferred):
   `java -jar --enable-preview earth.jar <cmd_line_args>` OR

2. Run the script: `./scripts/run.sh <cmd_line_args`

In both cases, you will see some warnings

### Compiling to native

GraalVM 23 is needed to compile to native. Run:

```shell
./scripts/build-native.sh
```

A binary named `earth` will be created in the root of the project.