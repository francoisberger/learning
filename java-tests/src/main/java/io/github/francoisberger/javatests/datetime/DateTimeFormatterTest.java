package io.github.francoisberger.javatests.datetime;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeFormatterTest {

	// Convert a "HH:MM:SS" with AM/PM parameter
	// to a 24 hour format.
	// 01:20:00PM -> 13:20:00
	// 12:02:00AM -> 00:02:00
	public static String timeConversion(String s) {
		String formatedTime = "";
		// DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.SHORT,
		// Locale.US);
		// Date clock = dateFormat.parse(s);
		// formatedTime = dateFormat.format(clock);
		LocalTime localTime = LocalTime.parse(s, DateTimeFormatter.ofPattern("hh:mm:ssa"));
		formatedTime = localTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
		System.out.println(s + " -> " + formatedTime);
		return formatedTime;
	}

	public static void main(String[] args) {
		DateTimeFormatterTest.timeConversion("01:20:00PM");
		DateTimeFormatterTest.timeConversion("12:02:00AM");

	}

}
