package io.github.francoisberger.javatests.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CollectResultsTest {

	public static void main(String[] args) {
		List<String> shoppingList = new ArrayList<String>();
		shoppingList.add("Coffee");
		shoppingList.add("Tea");
		shoppingList.add("Cookies");
		shoppingList.add("Tomatoes");
		shoppingList.add("Cream");

		List<String> filteredList = shoppingList.stream()
				.filter(item -> item.startsWith("C"))
				.map(item -> item.toUpperCase())
				.collect(Collectors.toList());

		System.out.println(filteredList);
	}

}
