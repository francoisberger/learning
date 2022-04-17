package io.github.francoisberger.javatests.other;

class Bird {
	void fly() {
		System.out.println("Flying...");
	}
}

public class AnonymousClassTest {

	public static void main(String[] args) {
		Bird bird1 = new Bird();
		bird1.fly(); // --> Displays "Flying..."

		Bird bird2 = new Bird() {
			@Override // Overriding the fly method for this particular bird
			void fly() {
				System.out.println("Flying fast...");
			}
		}; // <-- Don't forget the ;

		// Here we're using our bird with its overriden fly method
		bird2.fly(); // --> Displays "Flying fast..."
	}
}
