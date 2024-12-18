Add documentation because you will forget how to compile your own language

---
This error message:
Sanity Error on line 3: Function `add` returns `int`, but the yeet statement has
type `nada`

happened for:

```
fn main() {
    fn add(a: int, b: int) int {
        fn sub(c: int, d: int) int {
            yeet c - d
        }
    }
}
```
It's correct, but the yeet statement is added by the compiler, so it just 
makes it seem ... of. 
