package io.github.francoisberger.javatests.lambda;

/**
 * A functional interface has only one method which be defined at runtime.
 */
@FunctionalInterface
interface Printer {
	void print();
}

@FunctionalInterface
interface SmartPrinter {
	void printSomething(String something);
}

@FunctionalInterface
interface AnotherInterface {
	String returnSomething();
}

public class FunctionalInterfaceTest {

	public static void main(String[] args) {
		// The method is defined during object creation
		Printer printer = () -> {
			System.out.println("Whatever...");
		};
		printer.print(); // --> Whatever

		// The method behavior is defined here. We can choose a different name for our
		// parameter
		SmartPrinter smartPrinter = (theStringToBePrinted) -> {
			System.out.println("Printing " + theStringToBePrinted + "...");
		};
		smartPrinter.printSomething("a parameter"); // --> "Printing a parameter..."

		// The method behavior is defined here. We're using here an hard coded value but
		// we could perform some calculation based on input arguments
		AnotherInterface anotherIt = () -> "A returned value";
		System.out.println(anotherIt.returnSomething()); // --> "A returned value"
	}

}