package io.github.francoisberger.javatests.collection;

import java.util.ArrayList;
import java.util.List;

class MyObject {
	String name;

	public MyObject(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object other) {
		// if (other.getClass() != this.getClass()) {
		if (!(other instanceof MyObject)) {
			return false;
		} else {
			return name.equals(((MyObject) other).getName());
		}
	}
}

public class ListGetByValueTest {

	public static void main(String[] args) {
		List<MyObject> objects = new ArrayList<MyObject>();
		objects.add(new MyObject("Name 1"));
		objects.add(new MyObject("Name 2"));
		objects.add(new MyObject("Name 3"));
		objects.add(new MyObject("Name 4"));

		// If equals is not overriden than this will not work as we will look object
		// based
		// on the objet.equals method.
		MyObject objectToSearch = new MyObject("Name 3");
		System.out.println(
				"Index of " + objectToSearch.getName() + " is " + objects.indexOf(objectToSearch));
	}

}