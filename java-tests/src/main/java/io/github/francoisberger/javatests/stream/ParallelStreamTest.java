package io.github.francoisberger.javatests.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ParallelStreamTest {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		Random r = new Random();

		for (int i = 0; i < 5000000; i++) {
			list.add(r.nextInt(10000));
		}
		long startTime, count, endTime;
		startTime = System.currentTimeMillis();
		count = list.stream().distinct().count();
		endTime = System.currentTimeMillis();
		System.out.printf("stream().distinct().count() found %1$d in %2$d ms\n", count,
				endTime - startTime);

		startTime = System.currentTimeMillis();
		count = list.stream().distinct().parallel().count();
		endTime = System.currentTimeMillis();
		System.out.printf("stream().distinct().parallel().count() found %1$d in %2$d ms\n", count,
				endTime - startTime);

		startTime = System.currentTimeMillis();
		count = list.stream().parallel().distinct().count();
		endTime = System.currentTimeMillis();
		System.out.printf("stream().parallel().distinct().count() found %1$d in %2$d ms\n", count,
				endTime - startTime);
	}
}
