package io.github.francoisberger.javatests.regex;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlStyleTagExtractionTest {

	public static void main(String[] args) {
		// Prepare some basic input
		List<String> lines = Arrays.asList(new String[] { "<h1>Nayeem loves counseling</h1>",
				"<h1><h1>Sanjay has no watch</h1></h1><par>So wait for a while</par>",
				"<Amee>safat codes like a ninja</amee>",
				"<SA premium>Imtiaz has a secret crush</SA premium>" });
		// begin tag may be any printable character
		// content may be any printable character but <>/
		// end tag is a back reference to begin tag
		// \1 is back reference to group 1 -> end tag must match begin tag
		String searchRegex = "<(\\p{Print}+)>(\\p{Print}[^<>/]+)</(\\1)>";
		Pattern pattern = Pattern.compile(searchRegex);
		for (String line : lines) {
			Matcher matcher = pattern.matcher(line);
			int numberOfMatchingPatterns = 0;
			while (matcher.find()) {
				numberOfMatchingPatterns++;
				// Because we used a back reference, we are sure beginTag and endTag match !
				String beginTag = matcher.group(1);
				String contents = matcher.group(2);
				String endTag = matcher.group(3);
				System.out.println(contents);

//				System.out.println("start() : " + matcher.start());
//				System.out.println("end()   : " + matcher.end());
//				System.out.println("group() : " + matcher.group());
//				for (int i = 1; i <= matcher.groupCount(); i++) {
//					System.out.printf("group(%1$d) : %2$s\n", i, matcher.group(i));
//				}
			}
			if (numberOfMatchingPatterns == 0) {
				System.out.println("None");
			}
		}
	}
}
