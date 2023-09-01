package com.spring.boot.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class SpringBootExampleApplicationTests {

	@Autowired
	private Executor asyncTaskExecutor;

	@Test
	void contextLoads() {
	}


	@Test
	public void listAsyncTask() {
		List<Integer> integerList = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			integerList.add(i);
		}

		long start = System.currentTimeMillis();
		CompletableFuture.allOf(integerList.parallelStream().map(object -> CompletableFuture.runAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(new Random().nextInt(3));
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}, asyncTaskExecutor)).toArray(CompletableFuture[]::new)).join();

		System.out.println("cost time: " + (System.currentTimeMillis() - start));

	}
}
