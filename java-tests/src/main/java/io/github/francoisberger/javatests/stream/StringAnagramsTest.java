package io.github.francoisberger.javatests.stream;

import java.util.Arrays;
import java.util.stream.Collectors;

public class StringAnagramsTest {

	static boolean isAnagram(String a, String b) {
		// Complete the function
		if (a.length() != b.length()) {
			return false;
		}
		if (a.equalsIgnoreCase(b)) {
			return true;
		}
		String sortedA = Arrays.stream(a.toLowerCase().split(""))
				.sorted((s1, s2) -> s1.compareTo(s2))
				.collect(Collectors.joining(""));
		String sortedB = Arrays.stream(b.toLowerCase().split(""))
				.sorted((s1, s2) -> s1.compareTo(s2))
				.collect(Collectors.joining(""));
		System.out.println(a + " -> " + sortedA);
		System.out.println(b + " -> " + sortedB);
		if (sortedA.equals(sortedB)) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		/*
		 * Scanner scan = new Scanner(System.in); String a = scan.next(); String b =
		 * scan.next(); scan.close();
		 */
		String a = "asml";
		String b = "lmsa";
		boolean ret = isAnagram(a, b);
		System.out.println((ret) ? "Anagrams" : "Not Anagrams");
	}
}
