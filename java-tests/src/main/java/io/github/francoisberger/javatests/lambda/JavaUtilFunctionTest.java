package io.github.francoisberger.javatests.lambda;

import java.util.function.Consumer;

public class JavaUtilFunctionTest {

	public static void main(String[] args) {
		// The method behavior is defined here for our functional interface.
		// We can choose a different name for our parameter.
		SmartPrinter smartPrinter = (theStringToBePrinted) -> {
			System.out.println("Printing " + theStringToBePrinted + "...");
		};
		smartPrinter.printSomething("a parameter"); // --> "Printing a parameter..."

		// We're doing just the same with a pre defined interface
		// from the java.util.function package.
		Consumer<String> doTheSame = (theStringToBePrinted) -> {
			System.out.println("Printing " + theStringToBePrinted + "...");
		};
		doTheSame.accept("a parameter"); // --> "Printing a parameter..."

		// We're doing just the same with a pre defined interface
		// in an even more concise way (no braces because we're doing a single
		// operation).
		Consumer<String> stillTheSame = theStringToBePrinted -> System.out
				.println("Printing " + theStringToBePrinted + "...");
		stillTheSame.accept("a parameter"); // --> "Printing a parameter..."
	}

}