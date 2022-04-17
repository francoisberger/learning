package io.github.francoisberger.javatests.stream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Class computes text statistics : - total length - number of words (separated
 * by space or tab chars) - total number of letters (including numbers) - number
 * of vowels and consonants found in text - number of sentences (i.e. text
 * separated by points . ? ! ...)
 * 
 * @author Francois
 *
 */

public class TextStatsWithStreamsTest {
	private int textLength = 0;
	private int nbWords = 0;
	private int nbCharacters = 0;
	private int nbLetters = 0;
	private int nbDigits = 0;
	private int nbVowels = 0;
	private int nbConsonants = 0;
	private int nbSentences = 0;

	/**
	 * Reads each line of the input buffer.
	 * 
	 * @param buffer The buffer to be analyze
	 * @throws IOException
	 */
	public void analyze(String buffer) throws IOException {
		try (BufferedReader reader = new BufferedReader(new StringReader(buffer));) {
			for (String currentLine : reader.lines().collect(Collectors.toList())) {
				analyzeLine(currentLine);
			}
		}
	}

	/**
	 * Updates current statistics with contents of src string
	 * 
	 * @param src The line to analyze
	 */
	private void analyzeLine(String src) {
		textLength = textLength + src.length();

		// Count punctuation using regex -----------------------------------------
		// TODO : Regex should be ". " or ".$" so that an url or email adress is
		// considered as a word.
		Pattern puncts = Pattern.compile("[.?!]");
		Matcher matcher = puncts.matcher(src);
		nbSentences = nbSentences + (int) matcher.results().count();

		// Remove punctuation and spaces using regex -----------------------------
		// Regex is : [non punct blanks] one or more times
		Pattern anyWord = Pattern.compile("[^\\p{Punct}\\p{Blank}]+");
		matcher = anyWord.matcher(src);
		List<String> words = matcher.results().map(MatchResult::group).collect(Collectors.toList());

		// Not working : String cleanedSrc = matcher.group(0);
		// System.out.println("Group: " + cleanedSrc);

		// Word count ------------------------------------------------------------
		// 1. Using StringTokenizer
		// StringTokenizer tokenizer = new StringTokenizer(src, " \t,:.?!");
		// nbWords = nbWords + tokenizer.countTokens();
		// while (tokenizer.hasMoreTokens()) {
		// System.out.println(tokenizer.nextToken());
		// }
		// 2. Using Pattern Matcher and Stream -> Stream can only be used once
		// nbWords = nbWords + (int)matcher.results().count();
		// 3. Using collected results
		nbWords = nbWords + words.size();

		// Letters count ------------------------------------------------------------
		// 1. Remove punctuation and spaces to count letters and digits
		// String onlyChars = src.replaceAll("\\p{Punct}|\\p{Blank}", "");
		// nbLetters = nbLetters + onlyChars.length();
		// System.out.println(onlyChars);
		// 2. For each word count letters, vowels and consonants
		// Use pattern for consonants as they can not be accentuated
		Pattern numbers = Pattern.compile("\\p{Digit}{1}");
		Pattern letters = Pattern.compile("[^\\p{Digit}]{1}");
		Pattern consonants = Pattern.compile("[bcdfghjklmnpqrstvwxz]{1}");
		for (String currentWord : words) {
			int currentWordLength = currentWord.length();
			int currentWordLetters = (int) letters.matcher(currentWord.toLowerCase()).results()
					.count();
			int currentWordConsonants = (int) consonants.matcher(currentWord.toLowerCase())
					.results().count();
			int currentWordDigits = (int) numbers.matcher(currentWord).results().count();
			nbCharacters = nbCharacters + currentWordLength;
			nbLetters = nbLetters + currentWordLetters;
			nbConsonants = nbConsonants + currentWordConsonants;
			nbDigits = nbDigits + currentWordDigits;
			nbVowels = nbVowels + (currentWordLetters - currentWordConsonants);
		}
	}

	/**
	 * Returns the string representation of this objects. May be used to print the
	 * object to stdout for debugging purpose.
	 */
	@Override
	public String toString() {
		return "Text statistics: " + System.lineSeparator() + "- length: " + textLength
				+ System.lineSeparator() + "- words: " + nbWords + System.lineSeparator()
				+ "- characters: " + nbCharacters + System.lineSeparator() + "- letters: "
				+ nbLetters + System.lineSeparator() + "- digits: " + nbDigits
				+ System.lineSeparator() + "- vowels: " + nbVowels + System.lineSeparator()
				+ "- consonants: " + nbConsonants + System.lineSeparator() + "- sentences: "
				+ nbSentences;
	}

	/**
	 * Runs the program with some test string
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String args[]) throws IOException {
		// Arrange test buffer
		StringBuilder buffer = new StringBuilder();
		buffer.append("This is some sentence with a few words." + System.lineSeparator());
		buffer.append(
				"This is another sentence that will use more words and " + System.lineSeparator());
		buffer.append(
				"will use two lines. This is a third sentence starting right after the second one."
						+ System.lineSeparator());
		buffer.append(
				"And now we have the last sentence, that's a lot of words for a simple test !");

		TextStatsWithStreamsTest stats = new TextStatsWithStreamsTest();
		stats.analyze(buffer.toString());
		System.out.println(stats);
	}
}
