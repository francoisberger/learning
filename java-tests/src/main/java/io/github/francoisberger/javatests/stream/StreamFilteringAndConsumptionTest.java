package io.github.francoisberger.javatests.stream;

import java.io.IOException;
import java.util.Arrays;

public class StreamFilteringAndConsumptionTest {
	/**
	 * Runs the program with some test string
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String args[]) {
		// We want to print countries in uppercase with no
		// coutry that starts with the letter R, in ascending order
		String[] countries = new String[] { "France", "UK", "US", "Germany", "China", "India", "Australia", "Belgium",
				"Italia", "Spain", "Russia" };
		Arrays.asList(countries)
				.stream()
				.filter(name -> !name.startsWith("R"))
				.map(name -> name.toUpperCase())
				.sorted()
				.forEach(name -> System.out.println(name));
	}
}
