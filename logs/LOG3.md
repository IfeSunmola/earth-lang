# Log to keep track of any decisions I make

1. Bro, write tests!!!

2. Uhh, I'm thinking of using "null" as both a type and a value (expression)

3. Violently resisting the urge to add type inference.

4. One aspect of not planning properly seems to have caught up with me.  
   This crashed:
   ```
   var name: str = "Ife Sunmola"
   fn sayHello(){
      _ = print("Hello, " + name)
   }

   _ = sayHello()
   ```
   The reason is because according to our sanity checker, `sayHello` has
   access to `name`, but on the JVM level, `sayHello` is declared inside the
   main function, and recall that in earth-lang, everything NOT in a
   function (i.e) global scope, will by default be in the main method on the
   jvm level. So, two solutions I have now:
    1. Every line of code must be in a function. With this, we have to
       enforce a "main" function that will mark the start of the program. OR
    2. Make every variable declared in the global scope actually be declared
       outside the main method on the jvm level. Seems like a big source of
       bugs but we'll see.
       We also need to make sure every method has access to all the variables
       in the global scope

5. Mutability is coming back to bite me in the ass. I'm playing whack-a-mole
   with `where is this changing`

6. Antlr is making me wanna jump off someplace high. Fuck it, I'm writing my
   lexer/parser.  