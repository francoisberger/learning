package io.github.francoisberger.javatests.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class DifferentWaysToCreateStreams {

	public static void main(String[] args) {
		String[] names = new String[] { "Bob", "Jack", "Joe" };
		Stream<String> namesStream = Arrays.stream(names);

		List<String> shoppingList = new ArrayList<String>();
		shoppingList.add("Coffee");
		shoppingList.add("Cookies");
		Stream<String> shoppingListStream = shoppingList.stream();

		Stream<String> friendsStream = Stream.of("Timo", "Pumba");

	}

}
