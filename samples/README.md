1. Builtin types are: int, float, bool, str, and nada. nada is `null` or `nil`
   or `None`.
    1. Note that `nada` is both a type and an expression

2. Builtin functions:
    1. intToStr(int) str
    2. floatToStr(float) str
    3. boolToStr(bool) str
    4. yap(str) nada
    5. yapln(str) nada

3. Reserved keywords: var, when, else, fn, yeet, _, loop

4. Supported operations: Additions, subtractions multiplication, division,
   modulus, boolean operations such as `&&`, `||`, `==`, `!=`, `!`, etc.
    1. Note that some operations will automatically convert the result to a
       different type. E.g. all divisions will return a float and `2 + 4.0` will
       return a float.

5. A main function is required.

6. The language is strongly typed. So, this will fail (with a sanity error):
   ```
   fn main(){
      var name: str = "Ife"
      name = 23
   }
   ```

7. Since the type attached to a declaration has to match the type of the
   expression, adding `nada` as both a type and an expression made it
   impossible for a null pointer error to happen in the code. Obviously,
   this would blow up in our faces if we were to add more features to
   the language.