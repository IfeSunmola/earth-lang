# The earth language

An attempt at a compiler written in Java and generates JVM bytecode. No unit
tests so I don't plan to keep working on this.

It also has a shitty syntax but idc. I just waddnted to create something.

The lexer and parser are handwritten, but antlr was used at the beginning. I
switched halfway because it was getting more problematic than helpful.

You will find bugs :)

---

## Sample code

Only basic stuff was implemented: Variable declarations, when/else statements,
function definitions, function calls, and loops. Implementing arrays would
mean implementing some error handling for out of bounds access so ... nah.

See [the samples directory](./samples)

---

## Running locally

### Linux

Download from the releases page or this
link: https://github.com/IfeSunmola/earth-lang/releases/download/v0.0.1/earth_compiler_linux_x64.tar.xz,
and extract the contents.

### Windows

Download from the releases page or this
link: https://github.com/IfeSunmola/earth-lang/releases/download/v0.0.1/earth_compiler_windows_x64.zip,
and extract the contents.

### Mac

Burn your ***laptop*** or build from source

After downloading/extracting, cd into the created directory and run the
program. E.g.:

```shell
./earth samples/fizz_buzz.earth
``` 

---

## Building from source

There are two parts to the entire program: the (java) runtime and the
compiler. For the program to work as expected, the runtime and the compiler
needs to be in the same directory, and the runtime directory MUST be named
`earth_jre`. The runtime is created by using `jlink`.

> If you're on windows, you might have to use git bash or WSL.

### Creating the runtime

Clone the repo, and make sure you have java 23+ installed. In the root of the
project, run:

```shell
./scripts/create-runtime.sh
```

A directory named `earth_jre` will be created. It is a stripped down version of
the java 23 runtime environment with only the modules (java.base) that the
generated bytecode depends on.

### The compiler

You can build the compiler as either a jar file, or a native image. There's
a very noticeable difference in the speed of the jar file and the native image.

---

To build as a jar file, run the following in the root of the project:

```shell
./scripts/build-jar.sh
```

A (fat) jar named `earth.jar` will be created in the root of the project.
You can run the compiler with:

```shell
java --enable-preview -jar earth.jar samples/fizz_buzz.earth
```

---

To build as a native image, you need to have GraalVM 23+ installed.
See: https://www.graalvm.org/latest/reference-manual/native-image/ on
downloading/installing for your platform.

After GraalVM is installed, build the native image with:

```shell
./scripts/build-native.sh
```

A binary named `earth` will be created in the root of the project. You can run
the compiler with:

```shell
./earth samples/fizz_buzz.earth
```