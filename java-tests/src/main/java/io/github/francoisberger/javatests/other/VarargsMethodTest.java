package io.github.francoisberger.javatests.other;

public class VarargsMethodTest {
	static void add(int... numbers) {
		String operation = "";
		int result = 0;
		for (int i = 0; i < numbers.length; i++) {
			operation += (i + 1 < numbers.length) ? numbers[i] + "+" : numbers[i];
			result += numbers[i];
		}
		System.out.println(operation + "=" + result);
	}

	public static void main(String[] args) {
		add(1, 2);
		add(1, 2, 3);
		add(1, 2, 3, 4);
		add(1, 2, 3, 4, 5);
	}
}
