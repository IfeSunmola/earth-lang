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
}
