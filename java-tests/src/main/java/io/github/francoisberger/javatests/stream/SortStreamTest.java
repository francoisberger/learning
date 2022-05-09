package io.github.francoisberger.javatests.stream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SortStreamTest {
	static void miniMaxSum(List<Integer> arr) {
		// Sort the arrays and keep top N values
		long minimum = arr.stream()
				.sorted()
				.limit(arr.size() - 1)
				.map(i -> Long.valueOf(i.longValue()))
				.reduce(Long.valueOf(0), (a, b) -> (a + b));
		long maximum = arr.stream()
				.sorted(Comparator.reverseOrder())
				.limit(arr.size() - 1)
				.map(i -> Long.valueOf(i.longValue()))
				.reduce(Long.valueOf(0), (a, b) -> (a + b));
		System.out.printf("%1$d %2$d\n", minimum, maximum);
	}

	// Given five positive integers, find the minimum and maximum values that can be
	// calculated by summing exactly four of the five integers. Then print the
	// respective minimum and maximum values as a single line of two space-separated
	// long integers.
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		List<Integer> arr = Arrays.asList(new Integer[] { 1, 2, 3, 4, 5 });

		SortStreamTest.miniMaxSum(arr);
		// Expected min = 1 + 2 + 3 + 4 = 10
		// Expected max = 2 + 3 + 4 + 5 = 14
	}
}
