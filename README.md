# The money language

Absolutely no good reason for calling it `money`. I just needed a name for the
project and I saw a $20 bill on my desk.

## There are already so many JVM languages, why create another one?

1. Because I can

2. Because creating a language where I can do absolutely anything I want is
   a power move

3. Because it's really fun

## Goals

1. Create a working JVM (toy) language using the class file API.

2. Variable declarations, function calls, function declaration, basic
   arithmetic, and control flow operation such as if statements and loops.

3. Be able to compile and run the program in one command.

## Non-goals

1. To be a serious language. Most of the language decisions made will be
   based off vibes and feelings.

2. To go insane with type checking, type inference and other language
   features.

3. To create a fast and efficient compiler.

## Valid sample code

```
let name: str = "John" // let declaration, cannot be reassigned
var age: int = 20 // var declaration, can be reassigned
var newAge: int // valid, var declaration without assignment
newAge = 21 // valid, reassigning a var
let isAdult: bool = age >= 18 // valid, let declaration with expression
when isAdult {
    let name:str = "ife"
}
else when isBaby {
    let name:str = "ife"
}  
else {
    let name:str = "ife" 
}

fn add(a: int, b: int) int {
    yeet a + b
}

let sum: int = add(1, 2)

_ = println(sum)

loop var i : int = 0, i < 23, i = i + 1 {
    // code
}
```

## Invalid sample code

```
let name: str // invalid, let declaration without assignment
let name: str = 1 // invalid, type mismatch
let name = "John" // invalid, type inference not supported
``` 