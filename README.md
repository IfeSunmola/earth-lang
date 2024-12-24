# The earth language

An attempt at a compiler written in Java and generates JVM bytecode.

Shitty syntax but idc. I just wanted to create something.

You'll also most likely find a lot of bugs.

## Sample code

Only basic stuff was implemented: Variable declarations, when/else statements,
function definitions, function calls, and loops. Implementing arrays would
mean implementing some error handling for out of bounds access so ... nah.

See [the samples directory](./samples)

---

## Usage (Java 23+)

There are two parts to the entire program: the (java) runtime and the
compiler. For the program to work as expected, the runtime and the compiler
needs to be in the same directory, and the runtime MUST be named `earth-jre`.

### Creating the runtime

Make sure you have any JDK 23+ installed. In the root of the project, run:

```shell
./scripts/build-runtime.sh
```

A directory named `earth-jre` will be created. That is the runtime.

### The compiler

There are two options: Running the jar file, or using a native binary. I
initially wanted to make different binaries available for different
platforms but it's just too much headache.

1. Using the jar file: Run the `earth.jar` file provided in the root of the
   project with:
   ```shell
   java -jar --enable-preview earth.jar <cmd_line_args>
   ```
   We need the enable preview flag because the class file API is still in
   preview

2. Using a native image: GraalVM 23+ is required for this step. Run:
   ```shell
   ./scripts/build-native.sh
   ```
   A binary named `earth` will be created in the root of the project.