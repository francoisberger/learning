package io.github.francoisberger.javatests.algorithm;

import java.util.ArrayList;

class BookStore {
	Categories categories = new Categories();

	/**
	 * Adds a category to the book store.
	 * 
	 * @param category The category to be added.
	 */
	public void addCategory(Category category) {
		categories.add(category);
	}

	/**
	 * Main entry point to count books. This method will use the private recursive
	 * count.
	 * 
	 * @return Number of books within the store.
	 */
	public int countBooks() {
		return this.countBooks(categories);
	}

	/**
	 * Recursive count.
	 * 
	 * @param categories The categories to count.
	 * 
	 * @return Number of books in categories and sub-categories.
	 */
	private int countBooks(Categories categories) {
		int c = 0;
		for (Category category : categories) {
			c += category.getNumberOfBooks();
			c += this.countBooks(category.getSubCategories());
		}
		return c;
	}

	/**
	 * Prints this book store's structure. This will use the recursive print.
	 */
	public void print() {
		this.print(0, categories);
	}

	/**
	 * Recursive print of subcategories
	 * 
	 * @param level
	 * @param subCategories
	 * @return
	 */
	private void print(int level, Categories categories) {
		for (Category category : categories) {
			System.out.println("\t".repeat(level) + "+ " + category.getName() + " - " + category.getNumberOfBooks());
			this.print(level + 1, category.getSubCategories());
		}
	}
}

class Categories extends ArrayList<Category> {

	private static final long serialVersionUID = 5640164847989663558L;

}

class Category {
	private int numberOfBooks;
	private String name;
	private Categories subCategories = new Categories();

	public Category(String name, int numberOfBooks) {
		this.name = name;
		this.numberOfBooks = numberOfBooks;
	}

	/**
	 * Adds the given category as a sub-category of current category.
	 * 
	 * @param category The category to add
	 */
	public void addSubCategory(Category category) {
		subCategories.add(category);
	}

	public int getNumberOfBooks() {
		return numberOfBooks;
	}

	public String getName() {
		return name;
	}

	public Categories getSubCategories() {
		return subCategories;
	}
}

public class RecursiveTest {

	public static void main(String[] args) {
		BookStore store = new BookStore();

		// Add regular category
		store.addCategory(new Category("Children's", 5));

		// Add a category with one sub-category
		Category historycal = new Category("Historical", 5);
		historycal.addSubCategory(new Category("Historical fiction", 4));
		store.addCategory(historycal);

		// Add a category with two sub-categories and a sub-category having
		// sub-categories
		Category sciencefiction = new Category("Science fiction", 12);
		sciencefiction.addSubCategory(new Category("Hard SF", 5));

		Category space = new Category("Space", 6);
		space.addSubCategory(new Category("Space fantasy", 5));
		space.addSubCategory(new Category("Space western", 3));
		sciencefiction.addSubCategory(space);

		store.addCategory(sciencefiction);

		// Display the book store and count books.
		store.print();
		System.out.println("The bookstore has " + store.countBooks() + " books");
	}

}