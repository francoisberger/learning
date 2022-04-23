package io.github.francoisberger.javatests.other;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class CurrencyNumberFormatTest {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		double payment = scanner.nextDouble(); // For example 12324.134
		scanner.close();

		// Write your code here.
		NumberFormat usformatter = NumberFormat.getCurrencyInstance(Locale.US);
		String us = usformatter.format(payment);

		// https://www.iana.org/assignments/language-subtag-registry/language-subtag-registry
		Locale indiaLocale = new Locale("en", "IN");
		NumberFormat indiaformatter = NumberFormat.getCurrencyInstance(indiaLocale);
		String india = indiaformatter.format(payment);

		NumberFormat chinaformatter = NumberFormat.getCurrencyInstance(Locale.CHINA);
		String china = chinaformatter.format(payment);

		NumberFormat franceformatter = NumberFormat.getCurrencyInstance(Locale.FRANCE);
		String france = franceformatter.format(payment);

		System.out.println("US: " + us);
		System.out.println("India: " + india);
		System.out.println("China: " + china);
		System.out.println("France: " + france);
	}
}
