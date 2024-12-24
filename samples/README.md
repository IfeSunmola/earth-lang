1. Supported operations: Additions, subtractions multiplication, division,
   modulus, boolean operations such as `&&`, `||`, `==`, `!=`, `!`, etc.
    1. Note that some operations will automatically convert the result to a
       different type. E.g. all divisions will return a float. 2 + 4.0 will
       return a float.

2. A main function is required.

3. The language is strongly typed. So, this will fail (with a sanity error):
   ```
   fn main(){
      var name: str = "Ife"
      name = 23
   }
   ```

4. The types are: int, float, bool, str, and nada. nada is `null` or `nil`
   or `None`.

    1. Since the type attached to a declaration has to match the type of the
       expression, adding `nada` as both a type and an expression made it
       impossible for a null pointer error to happen in the code. Obviously,
       this would blow up in our faces if we were to add more features to
       the language.