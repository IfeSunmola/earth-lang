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

4. Just spent over 6 hours trying to solve a nasty bug ... and the annoying
   part? For the first 5 hours 30 minutes, I was looking at the wrong part
   of the code base.
   It was when I was trying to implement when/else-when/else. The logic
   I was using was correct, but because I didn't properly implement
   visitRelationalExpr in ExprCodegen, the program kept crashing because
   of:
   ```
   Exception in thread "main" java.lang.IllegalArgumentException: Detected 
   branch target out of bytecode range at bytecode offset 18 of method main(String[])
   0000: 12 2d 3c 1b 12 2d 1b 12 2d a4 00 07 04 a7 00 04
   0010: 03 04 a0 ff ed 12 2f b8 00 30 a7 00 03 b1
   ...
   ```
   What a generous and helpful error message. Debugger was useless too. In
   visitRelationalExpr, I was loading a value twice