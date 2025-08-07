## Earth Language Playground (server)

### API

There are two endpoints available in the server:

1. `POST /run`
    - Takes the source code to run as a raw text (text/plain)
    - Returns the result as json (application/json). E.g:
      ```json
      {
        "isSuccess": true,
        "msg": "Hello World!"
      }
      ```
    - 500 Internal Server Error on any failure
    - 200 OK for everything else
    - Sample curl:
      ```shell
      curl --request POST \
      --url http://localhost:8080/run \
      --header 'Content-Type: text/plain' \
      --data 'fn main (){
      _ = yapln("Hello, World!")
      }'
      ```

2. `GET /samples`
    - Returns a list of sample programs available in the server in form of:
      ```json
      [
        {
          "name": "Sample Name",
          "code"  : "fn main() { ... }"
        },
        {
          "name": "Another Sample",
          "code"  : "fn main() { ... }"
        }
      ]
      ```
    - 200 OK
    - Sample curl:
      ```shell
      curl --request GET \
      --url http://localhost:8080/samples
      ```

---

Similar to the compiler directory, the jre AND the binary needs to be in
the same directory for the server to work. This is because the server
directly executes the binary. So the directory structure for the server
structure should look like:

```
web-server/
  ├── earth_jre/
  ├── src
  ├── earth (binary)
  ├── other files/directories ...
```

## Running the server

The server runs on port 8080 and can be started by cloning the project and
running:

```shell
./mvnw spring-boot:run
# or for windows:
./mvnw.cmd spring-boot:run
```

## Building the server

### As a jar file

To build the server as a jar files, run:

```shell
./mvnw clean package
# or for windows:
./mvnw.cmd clean package
```

> The command also runs the tests

The server will be built as a jar file and placed in the `target` directory.
You can run it with:

```shell
java -jar target/web-server-0.0.1.jar
```

### As a native image

To build the server as a native image, you need to have GraalVM installed and
configured in your environment. Follow the steps
on: https://www.graalvm.org/jdk24/getting-started/

Then run:

```shell
./mvnw clean -Pnative native:compile
# or for windows:
./mvnw.cmd clean -Pnative package
```

> The command also runs the tests

An executable named `web-server` will be created in the `target` directory,
which can be executed with:

```shell
./target/web-server
```