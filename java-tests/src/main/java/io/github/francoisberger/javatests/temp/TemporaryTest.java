package io.github.francoisberger.javatests.temp;

import java.util.Hashtable;

/**
 * Just put in here whatever test you need. Do not hesitate to replace !
 * 
 * @author Francois
 *
 */
public class TemporaryTest {

	public static String caesarCipher(String s, int k) {
		k = k % 26;
		Hashtable<Integer, Integer> cipher = new Hashtable<Integer, Integer>();
		// Build cipher
		for (int letter = 0; letter < 26; letter++) {
			int cipheredLetter = 0;
			if (letter + k < 26) {
				cipheredLetter = letter + k;
			} else {
				int distanceAfterZ = (letter + k) - 26;
				cipheredLetter = distanceAfterZ;
			}
			cipher.put('a' + letter, 'a' + cipheredLetter);
			cipher.put('A' + letter, 'A' + cipheredLetter);
		}
		String output = s.chars().map(letter -> {
			if (cipher.containsKey(letter)) {
				return cipher.get(letter);
			} else {
				return letter;
			}
		})
				.collect(StringBuilder::new, // supplier
						StringBuilder::appendCodePoint, // accumulator
						StringBuilder::append) // combiner
				.toString();
		return output.toString();
	}

	public static void main(String[] args) {

	}

}
