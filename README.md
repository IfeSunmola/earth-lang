# The earth language

A toy compiler written in Java and generates JVM bytecode.

Most of the decisions made was based off vibes and feelings so don't ask why
something is the way it is.

## Valid sample code

```
var name: str = "John"
var age: int = 20
age = 21
var isAdult: bool = age >= 18
when name == "John" {
    /*
    function calls are expressions, and all expressions must be bound to a 
    statement. Since println returns void, we can just ignore it with an unnamed
    statement.
    */
    _ = println("Hello John")
}
else when name == "Doe" {
    _ = println("Hello Doe")
}  
else {
    var greeting: str = "Hello" + name
    _ = println(greeting)  
}

fn add(a: int, b: int) int {
    yeet a + b // return statement
}

var sum: int = add(1, 2)
// println can only take a string, so convert. There's floatToStr and boolToStr. 
_ = println(intToStr(sum))

loop var i : int = 0, i < 23, i = i + 1 {
    // code
}
```