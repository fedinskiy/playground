package ru.fedinskiy;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Coders {

	public static void main(String[] args) {
		List<Statistic> stats = Stream.of(new Coder(20, 40),
				new Coder(30, 60),
				new Coder(20, 70),
				new Coder(40, 100))
				.collect(Collectors.groupingBy(Coder::getAge))
				.entrySet().stream()
				.map(entry -> {
					int age = entry.getKey();
					double rate = entry.getValue().stream().mapToInt(Coder::getRate).average().getAsDouble();
					return new Statistic(age, rate);
				})
				.collect(Collectors.toList());
		System.out.println(stats);
	}
}

class Statistic {
	private final int age;
	private final double rate;

	Statistic(int age, double rate) {
		this.age = age;
		this.rate = rate;
	}

	public int getAge() {
		return age;
	}

	public double getRate() {
		return rate;
	}

	@Override
	public String toString() {
		return "Coder{" +
				"age=" + age +
				", rate=" + rate +
				'}';
	}
}

class Coder {
	private final int age;
	private final int rate;

	Coder(int age, int rate) {
		this.age = age;
		this.rate = rate;
	}

	public int getAge() {
		return age;
	}

	public int getRate() {
		return rate;
	}

	@Override
	public String toString() {
		return "Coder{" +
				"age=" + age +
				", rate=" + rate +
				'}';
	}
}
