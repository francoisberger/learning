package io.github.francoisberger.javatests.regex;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTests {
	static void IPAdressTest() {
		// IP Adress matcher
		// (0 -> 199 or 200 -> 249 or 250 -> 255). exactly 3 times + (0 -> 199 or 200 ->
		// 249 or 250 -> 255) exactly 1 times
		String regex = "(([0-1]?[0-9]?[0-9]?|2[0-4][0-9]|25[0-5])\\.){3}([0-1]?[0-9]?[0-9]?|2[0-4][0-9]|25[0-5]){1}";
		System.out.println("168.1.1.1".matches(regex));
	}

	static void usernameTest() {
		// A user name is 8 to 30 characters long
		// The username can only contain alphanumeric characters and underscores (_)
		// The first character must be an alphabetic character, i.e., either lowercase
		// character a-z or uppercase character A-Z
		String regex = "[a-zA-Z]{1}[a-zA-Z0-9_]{7,29}";
		System.out.println("Bob_31".matches(regex));
		System.out.println("Invalid?72".matches(regex));
		System.out.println("1nvalid39".matches(regex));
	}

	static void repeatedWordsTest() {
		StringBuffer strings = new StringBuffer();
		strings.append("5" + System.lineSeparator());
		strings.append("Goodbye bye bye world world world" + System.lineSeparator());
		strings.append("Sam went went to to to his business" + System.lineSeparator());
		strings.append("Reya is is the the best player in eye eye game" + System.lineSeparator());
		strings.append("in inthe" + System.lineSeparator());
		strings.append("Hello hello Ab aB" + System.lineSeparator());

		// A sentence is words (letters) separated by spaces
		// First group -> a word
		// \b: look for word boundary (match only beginning of word instead of somewhere
		// in the middle);
		// Second group -> some space + first group repeated
		// \b: like in step 1 – make sure it’s not a part of some longer word;
		// +: match one or more occurrence of group 1
		String regex = "\\b(\\p{Alpha}+)(\\p{Space}+\\1\\b)+";
		Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

		// Scanner in = new Scanner(System.in);
		Scanner in = new Scanner(strings.toString());
		int numSentences = Integer.parseInt(in.nextLine());

		while (numSentences-- > 0) {
			String input = in.nextLine();

			Matcher m = p.matcher(input);

			// Check for subsequences of input that match the compiled pattern
			while (m.find()) {
				// System.out.println("group() : " + m.group());
				// System.out.println("group(1) : " + m.group(1));
				// System.out.println("group(2) : " + m.group(2));
				input = input.replaceAll(m.group(), m.group(1));
			}

			// Prints the modified sentence.
			System.out.println(input);
		}

		in.close();
	}

	// Password must be 6 chars long with : one digit, one lower case, one upper case, one special
	static int checkPassword(String password) {
		// Return the minimum number of characters to make the password strong
		int missingLength = 0;
		if (password.length() < 6) {
			System.out.println("Too short");
			missingLength = 6 - password.length();
		}
		int requiredLength = 0;
		if (!password.matches(".*[0-9]+.*")) {
			requiredLength++;
		}
		if (!password.matches(".*[a-z]+.*")) {
			requiredLength++;
		}
		if (!password.matches(".*[A-Z]+.*")) {
			requiredLength++;
		}
		// Here we escape all chars with \\Q and \\E
		if (!password.matches(".*[\\Q!@#$%^&*()-+\\E]+.*")) {
			requiredLength++;
		}
		if (missingLength == 0) {
			return requiredLength;
		} else if (requiredLength < missingLength) {
			return missingLength;
		} else {
			return requiredLength;
		}
	}

	public static void main(String[] args) {
		// IPAdressTest();
		/// usernameTest();
		repeatedWordsTest();
	}

}
