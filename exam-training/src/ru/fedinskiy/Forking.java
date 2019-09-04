package ru.fedinskiy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Forking {
	ForkJoinPool pool = new ForkJoinPool();

	public static void main(String[] args) throws IOException {
		new Forking().process();
	}

	public void process() throws IOException {
		assert 10 == calc("1", "22", "333", "4444");
		;
		assert 6 == calc("1", "22", "333");
		assert 3 == calc("1", "22");

		Stream<char[]> stream = Stream.of(" ".toCharArray());
		BiFunction<String, String, String[]> splitter = String::split;
		Function<String, String[]> spaceSplitter = str -> splitter.apply(str, " ");
		Consumer<Object> printer = System.out::println;
		Predicate<String> isEmpty = String::isEmpty;
		List<String> words = Files.lines(Paths.get("заметки.txt")).map(spaceSplitter).flatMap(Stream::<String>of)
				.filter(isEmpty.negate())
//				.limit(1200)
//				.peek(printer)
				.collect(Collectors.toList());
//		long count = words.stream().map(String::length).reduce(0,(a,b)->a+b);
//		System.out.println(count);
		printer.accept(calc(words));
		printer.accept(innerCalc(words));
	}

	private int calc(String... strings) {
		return calc(Arrays.asList(strings));
	}

	private int calc(List<String> strings) {
		return pool.invoke(getCalculator(strings));
	}

	private int innerCalc(List<String> strings) {
		StringLengthInnerCalculator task = getInnerCalculator(strings);
		pool.invoke(task);
		System.out.println(task.getSimple());
		return task.getSimple();
	}

	private static StringLengthCalculator getCalculator(List<String> strings) {
		return new StringLengthCalculator(strings);
	}
	private static StringLengthInnerCalculator getInnerCalculator(List<String> strings) {
		return new StringLengthInnerCalculator(strings);
	}

}

class StringLengthInnerCalculator extends RecursiveAction {
	private final List<String> source;
	private final int start;
	private final int end;
//	private static AtomicInteger atomicResult = new AtomicInteger(0);
	private int result;

	StringLengthInnerCalculator(List<String> source) {
		this(source, 0, source.size());
	}

	private StringLengthInnerCalculator(List<String> source, int start, int end) {
		this.source = source;
		this.start = start;
		this.end = end;
	}

	@Override
	protected void compute() {
		if (end - start <= 3) {
			Integer fin = source.subList(start, end).stream().reduce(0, (i, s) -> s.length() + i, Math::addExact);
//			atomicResult.addAndGet(fin);
			result +=fin;
		} else {
			int mid = start + ((end - start) / 2);
			StringLengthInnerCalculator first = new StringLengthInnerCalculator(source, start, mid);
			StringLengthInnerCalculator second = new StringLengthInnerCalculator(source, mid, end);
			invokeAll(first, second);
			result+=first.getSimple()+second.getSimple();
		}
	}

/*	public int getResult() {
		return atomicResult.get();
	}*/
	public int getSimple() {
		return result;
	}
}

class StringLengthCalculator extends RecursiveTask<Integer> {
	private final List<String> source;
	private final int start;
	private final int end;

	StringLengthCalculator(List<String> source) {
		this(source, 0, source.size());
	}

	private StringLengthCalculator(List<String> source, int start, int end) {
		this.source = source;
		this.start = start;
		this.end = end;
	}

	@Override
	protected Integer compute() {
		if (end - start <= 3) {
			return source.subList(start, end).stream().reduce(0, (i, s) -> s.length() + i, Math::addExact);
		} else {
			int mid = start + ((end - start) / 2);
			StringLengthCalculator first = new StringLengthCalculator(source, start, mid);
			StringLengthCalculator second = new StringLengthCalculator(source, mid, end);
			first.fork();
			try {
				return second.compute() + first.get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
//				Thread.currentThread().interrupt();
				return 0;
			}
		}

	}
}