package ru.fedinskiy;

import java.text.MessageFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

class Nurture extends ReentrantLock {
	private final String name;

	Nurture(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}

class Food extends Nurture {
	public Food() {
		super("Food");
	}
}

class Water extends Nurture {
	public Water() {
		super("Water");
	}
}

public class Fox {
	private final String name;
	private boolean fed;


	public Fox(String name) {
		this.name = name;
	}

	public void drinkAndEat(Nurture first, Nurture second) {
		try {
			do {
				if (!(fed = obtain(first, second))) {
					int running=0;
					running+=move();
					running+=move();
					if(running==7){
						fed = obtain(second, first);
					}
					move();
				}

			} while (!fed);
		}catch (InterruptedException ex){

		}
	}

	private boolean obtain(Nurture first, Nurture second) throws InterruptedException {
		System.out.println(MessageFormat.format("{0} trying {1} before {2}!",
				name,
				first.getName(),
				second.getName()));
		if (first.tryLock(1000, TimeUnit.MILLISECONDS)) {
			synchronized (first) {
				System.out.println(MessageFormat.format("{0} got {1}!", name, first.getName()));
				move();
				if (second.tryLock(10, TimeUnit.MILLISECONDS)) {
					synchronized (second) {
						System.out.println(MessageFormat.format("{0} got {1}!", name, second.getName()));
						return true;
					}
				}
			}
		}
		return false;
	}

	public int move() {
		try {
			Thread.sleep(100);

		} catch (InterruptedException e) {
			// Handle exception
		}
		return 1;
	}

	public void run() {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// Handle exception
		}
	}

	public static void main(String[] args) throws InterruptedException {
		// Create participants and resources
		Fox foxy = new Fox("Foxy");
		Fox tails = new Fox("Tails");
		Food food = new Food();
		Water water = new Water();

		// Process data
		ExecutorService service = null;
		try {
			service = Executors.newScheduledThreadPool(10);
			service.submit(() -> foxy.drinkAndEat(food, water));
			service.submit(() -> tails.drinkAndEat(water, food));
		} finally {
			if (service != null) service.shutdown();
		}
	}
}
