# The earth language

An attempt at a compiler written in Java and generates JVM bytecode. I
physically cannot stress enough how stressful this was. I do not plan to keep
working on this. Still fun though.

The syntax is also not the most intuitive but that's the freedom of creating
something yourself. You can do whatever tf you want.

The lexer and parser are handwritten, but antlr was used at the beginning. I
switched halfway because it was getting more problematic than helpful.

You will find bugs, that's a promise.

## Sample code

Only basic stuff was implemented: Variable declarations, when/else statements,
function definitions, function calls, and loops. Implementing arrays would mean
implementing some error handling for out of bounds access so ... nah.

See [the samples directory](./samples)

## Running locally

As this is not something that's meant to be used for anything serious, I did
not want to mess with path and environment variables.

### Linux and Windows

Download your OS archive file from
the [release page](https://github.com/IfeSunmola/earth-lang/releases) and
extract the contents.

After downloading/extracting, cd into the created directory and run a sample
program. E.g.:

```shell
./earth samples/hello_world.earth
```

### Mac

Build from source or (and I'm dead serious) burn your ***laptop***.

## Building from source

There are two parts to the entire program: the (java) runtime and the
compiler. For the program to work as expected while not messing with path
and environment variables, the runtime and the compiler needs to be in the same
directory, and the runtime directory MUST be named earth_jre. The runtime is
created by using jlink. A valid folder structure would look like this:

```
 ├── earth_jre/
 └── earth binary or source code or jar file
```

> If you're on windows, you might have to use git bash or WSL

### Creating the runtime

Ensure you have java 24+ on your path and run the following commands

```bash
git clone git@github.com:IfeSunmola/earth-lang.git
cd compiler

modules=("java.base")
# Create a custom runtime image
jlink \
  --no-header-files --no-man-pages \
  --add-modules "${modules[@]}" \
  --output earth_jre
```

At this point, you can run the program directly with the source code with:

```bash
./mvnw -q compile exec:java -Dexec.args=samples/fizz_buzz.earth
# or on windows
./mvnw.cmd -q compile exec:java -Dexec.args=samples/fizz_buzz.earth
```

If you make changes to the source code, the command above is how you can
execute the program. (or you can just use intellij to run the Main class)

### Creating an executable

You can build the compiler as either a jar file, or a native image. There's a
very noticeable difference in the speed of the jar file and the native image.

---
To build as a jar file, run:

```shell
./mvnw clean package
# or on windows
./mvnw.cmd clean package 
```

A `earth-0.0.1.jar` file will be created in the target directory, which can be
executed with a sample code like:

```shell
java -jar target/earth-0.0.1.jar samples/if_test.earth
```

---
To build as a native image, you need to have graalvm on your path.
Graalvm is a JDK distribution that has a native image compiler. Follow the
steps on: https://www.graalvm.org/jdk24/getting-started/

Then run:

```shell
./mvnw clean -Pnative package
# or on windows
./mvnw.cmd clean -Pnative package 
```

An executable named `earth` will be created in the target directory, which
can be executed with a sample code like:

```shell
./target/earth samples/if_test.earth
```