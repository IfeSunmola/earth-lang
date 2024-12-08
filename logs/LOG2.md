# Log to keep track of any decisions I make

1. I should probably include unit tests early on to safeguard my sanity but
   oh well.

2. Another dumb decision made! I'm a bit too lazy to find a way to make
   `print` or `println` work with all types, so my genius solution is to
   create both methods that only works with String, and add builtin
   functions like `intToStr` and `floatToStr` to convert other types. So,
   this is how you'll print an integer, EVERY TIME
   ```
   _ = println(intToStr(23))
   ```
   My room temperature IQ is starting to show

3. Not sure on how to handle using multiple `yeet` statements