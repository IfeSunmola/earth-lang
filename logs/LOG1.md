# Log to keep track of any decisions I make

1. I have the choice to bake in `true` and `false` as keywords, but I think
   I'll use them as identifiers instead. I don't want to tie too much of the
   language to the grammar. What the flip am I even saying?

2. I've decided to not have a statement terminator, and instead use a new
   statement as a terminator.
   E.g:
   ```
   // the when statement will be treated as the end of the var statement
   var name: str = "ife" 
   when name == "ife" {
       _ = println("Hello, Ife")
   }
   ```
   Will this be problematic? Definitely but I like to fuck around and find out.

3. For simplicity, `if` will be statements. If they're expressions, then I
   have to handle the case where different branches returns different type ...
   which essentially opens up the door to union types.

4. Actually, i've decided to use `when`/`else when/ instead of `if`/`else
   if`. No reason for this.

5. `yeet` -> `return`

6. I decided to do this idiotic thing as my own type of `expression statement`,
   called `unnamed statement`:
   ```
   // compilation failed because only statements can begin a new statement, 
   // and all expressions have to be linked to a statement
   println("Hello") 
   // So, if we're calling a function, we HAVE to attach it to a statement like:
   var name: str = nameMaker("Ife", "Sunmola")
   // Since println returns nothing, we can't assign it to an assignment 
   // statement. So we do this:
   _ = println("Hello") 
   ```
   This looks like it would get ugly quick but as I said earlier, I like
   fucking around and finding out

7. I just got the most nasty idea. What if ... operators like +, -, <=, >=,
   e.t.c. were not directly baked into the language but instead, they're
   built as functions? AHHHHHH. I think I've seen it in another language
   before. Scala?
    1. This would mean I have to have a concept of infix functions. So, for
       example, the `+` function would be an infix function.
        1. An infix function is a function that can be called without the
           dot and parentheses. E.g. `a + b` instead of `a.+(b)`
    2. Nope, too complicated, I'm not doing this. 