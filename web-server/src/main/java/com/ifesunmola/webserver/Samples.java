package com.ifesunmola.webserver;

import java.util.List;

class Samples {
	static String empty() {
		return """
			fn main() {
			
			}
			""";
	}

	static String helloWorld() {
		return """
			fn main(){
			    _ = yapln("Hello, World!")
			}
			""";
	}

	static String fizzBuzz() {
		return """
			// For numbers 1 to n, print all the numbers, but if the number is divisible
			// by 3, print "Fizz" instead, and if the number is divisible by 5, print "Buzz"
			// instead. If the number is divisible by both 3 and 5, print "FizzBuzz" instead
			
			fn main(){
			    var n: int = 100
			
			    loop var i: int = 1, i <= n, i = i + 1{
			        when i % 3 == 0 && i % 5 == 0{
			            _ = yapln("FizzBuzz")
			        }
			        else when i % 3 == 0 {
			            _ = yapln("Fizz")
			        }
			        else when i % 5 == 0 {
			            _ = yapln("Buzz")
			        }
			        else {
			            _ = yapln(intToStr(i))
			        }
			    }
			}
			""";
	}

	static String whenElse() {
		List<String> weatherTypes = List.of(
			"Sunny", "Rainy", "Cloudy", "Rice"
		);
		String weather = weatherTypes
			.get((int) (Math.random() * weatherTypes.size()));
		return """
			fn main () {
			     var weather: str = "%s"
			     var result: str = "Today's weather is: "
			
			     when weather == "Sunny" {
			         result = result + "Sunny"
			     }
			     else when weather == "Rainy" {
			         result = result + "Rainy"
			     }
			     else when weather == "Cloudy" {
			         result = result + "Cloudy"
			     }
			     else {
			         result = "Uhh, I don't know what '" + weather + "' is"
			     }
			
			     _ = yapln(result)
			 }
			""".formatted(weather);
	}

	static String fibonacci() {
		return """
			fn fibonacci(n: int) int {
			    var a: int = 0
			    var b: int = 1
			
			    loop var i: int = 0, i < n, i = i + 1 {
			        var temp: int = a
			        a = b
			        b = temp + b
			    }
			    yeet a
			}
			fn main () {
			    var num: int = 10
			    _ = yapln("Fibonacci of " + intToStr(num) + " is: " + intToStr(fibonacci(num)))
			
			     num = 46
			     _ = yapln("Fibonacci of " + intToStr(num) + " is: " + intToStr(fibonacci(num)))
			     _ = yapln("Any higher than " + intToStr(num) + " will cause an overflow in the current implementation. (:")
			
			    num = 19
			    _ = yapln("But we can do " + intToStr(num) + " without overflow: " +
			    intToStr(fibonacci(num)) + " :)")
			}
			""";
	}

	static String full() {
		return """
			fn sayHello(name: str) {
			    var greeting: str = "Hello " + name
			    _ = yapln(greeting)
			}
			
			fn add(a: int, b: float)float {
			    yeet a + b // yeet is equivalent to `return`
			}
			
			fn findMin(a: int, b: int)int {
			    // When statements cannot directly yeet values, so we use a temp variable
			    var result: int = 0
			
			    when a < b {
			        result = a
			    }
			    else {
			        result = b
			    }
			    yeet result
			}
			
			fn isDivisible(a: int, b: int)bool {
			    yeet a % b == 0
			}
			
			fn isBetween(a: int, b: float, c: int)bool {
			    yeet a < b && b < c
			}
			
			fn main(){
			    // All expressions must be attached to a statement. Since `yapln` returns
			    // `nada`, we can attach it to an unnamed statement with _.
			    // Below is equivalent to `var result: nada = sayHello("Cassi")`
			    _ = sayHello("Cassi")
			    var result: nada = sayHello("Cassi")
			
			    var sum: float = add(1, 2.9)
			    // yapln only accepts strings, so we must convert.
			    // boolToStr intToStr are also available
			    _ = yapln(floatToStr(sum))
			
			    var min: int = findMin(1, 2)
			    var msg: str = "The minimum is: " + intToStr(min)
			    _ = yapln(msg)
			
			    var isDiv: bool = isDivisible(10, 5)
			    var divMsg: str = "10 is divisible by 5: " + boolToStr(isDiv)
			    _ = yapln(divMsg)
			
			    var isBetween: bool = isBetween(1, 2.5, 3)
			    var betweenMsg: str = "2.5 is between 1 and 3: " + boolToStr(isBetween)
			    _ = yapln(betweenMsg)
			
			    var age: int = 100
			    when age < 18 {
			        _ = yapln("You're still a kid")
			    }
			
			    else when age < 30 {
			        _ = yapln("You're still young")
			    }
			
			    else when age < 50 {
			        _ = yapln("You're getting old")
			    }
			
			    else {
			        _ = yapln("You're really old")
			    }
			}
			""";
	}
}
