package io.github.francoisberger.javatests.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class DifferentWaysToCreateStreamsTest {

	public static void main(String[] args) {
		String[] names = new String[] { "Bob", "Jack", "Joe" };
		Stream<String> namesStream = Arrays.stream(names);
		namesStream.forEach(name -> System.out.println(name));

		List<String> shoppingList = new ArrayList<String>();
		shoppingList.add("Coffee");
		shoppingList.add("Cookies");
		Stream<String> shoppingListStream = shoppingList.stream();
		shoppingListStream.forEach(item -> System.out.println(item));

		Stream<String> friendsStream = Stream.of("Timo", "Pumba");
		friendsStream.forEach(friend -> System.out.println(friend));
	}

}
