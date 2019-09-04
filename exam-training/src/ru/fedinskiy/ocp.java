package ru.fedinskiy;

import java.awt.event.ActionEvent;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.time.Duration;
import java.time.Period;
import java.util.*;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class CharacterWrapperStream extends InputStream {
	final Reader inner;

	CharacterWrapperStream(Reader inner) {
		this.inner = inner;
	}

	@Override
	public int available() throws IOException {
		return inner.ready() ? 1 : 0;
	}

	@Override
	public int read() throws IOException {
		return inner.read();
	}
}

class Deleter {
	static void delete(Path path) {
		try {
			Files.delete(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

class TestClass {
	public TestClass(int i) {
	}

	public void m1() {
		TestClass al = new TestClass(10) {
			public void actionPerformed(ActionEvent e) {

			}
		};
	}
}

interface Samurai {
	static void fidelity() {
	}

	abstract String katana();
}

class Book {
	private String title;
	private Double price;

	public String getTitle() {
		return title;
	}

	public Double getPrice() {
		return price;
	}

	public Book(String title, Double price) {
		this.title = title;
		this.price = price;
	}
}

interface I1 {
	void m1() throws java.io.FileNotFoundException;
}

interface I2 {
	void m1() throws java.sql.SQLException;
}

class Implementor implements I1, I2 {

	@Override
	public void m1() {

	}
}

interface One extends Two {
	public default String getId() {
		return "ISBN123456";
	}
}

interface Two {
	public static String getId() {
		return "ISBN123456";
	}

}

public class ocp {
	class Inner {

	}


	public static boolean isValid(Path p) {

		return p.endsWith(".dat");
	}

	public static void writeData() {
		Path p1 = Paths.get("/temp/records");
		Path p2 = p1.resolve("clients.dat");
		System.out.println(p2 + " " + isValid(p2));

	}

	public static void main(String args[]) throws IOException {
				TreeMap
		Integer result = Stream.of(1, 2, 3).collect(Collectors.summingInt(Integer::intValue));
		System.out.println(result);
	}


	public static void copy(String records1, String records2) {

	}

	public static void streaming() {
		List<Integer> ls = Arrays.asList(10, 12);
		Integer sum = ls.stream().map(a -> a += 10).reduce(0, (a, b) -> a + b);
		System.out.println(sum);
	}

	public static void comparing() throws IOException {
		Comparator<Book> byPrice = (final Book b1, final Book b2) -> (int) Math.round(b1.getPrice() - b2.getPrice());

	}

	public static void writing() throws IOException {
		try (FileWriter writer = new FileWriter("test.txt")) {
//			writer.write("is it works?");
		}
	}

	public static void treeing() {
		TreeSet<Integer> set = new TreeSet<>();
		set.add(1);
		set.add(2);
		set.add(4);
		set.add(8);

		assert set.lower(0) == null;
		assert set.lower(5) == 4;
		assert set.lower(4) == 2 : set.lower(4);

		assert set.floor(0) == null;
		assert set.floor(5) == 4;
		assert set.floor(4) == 4;

		assert set.higher(9) == null;
		assert set.higher(5) == 8;
		assert set.higher(4) == 8 : set.lower(4);

		assert set.ceiling(9) == null;
		assert set.ceiling(5) == 8;
		assert set.ceiling(4) == 4;
		SortedSet<Integer> tail = set.tailSet(3);
		NavigableSet<Integer> longtail = set.tailSet(3, true);
		System.out.println(tail);
		tail.add(0);
		System.out.println(tail);
	}

	public static void mapping() {
		Map<String, Integer> map = new HashMap<>();

//		UnaryOperator<Integer> nullTo0 = i -> Objects.isNull(i) ? 0 : i;
		ToIntFunction<Integer> nullTo0 = i -> Objects.isNull(i) ? 0 : i;
		IntFunction<String> len = i -> "" + i;
		map.put("1", 1);
		map.merge("1", 5, (first, second) -> first + second); //6
		assert map.get("1") == 6;
		map.merge("5", 5, (first, second) -> first + second); //five=5
		assert map.get("5") == 5;

		map.put("2", 2);
		map.compute("2", (string, integer) -> string.length() + nullTo0.applyAsInt(integer)); //
		assert map.get("2") == 3;
		map.compute("6", (string, integer) -> string.length() + nullTo0.applyAsInt(integer));
		assert map.get("6") == 1;

		map.put("3", 3);
		map.computeIfAbsent("3", String::length);
		assert map.get("3") == 3;
		map.computeIfAbsent("7", String::length);
		assert map.get("7") == 1;

		map.put("4", 4);
		map.computeIfPresent("4", (string, integer) -> string.length() + integer);
		assert map.get("4") == 5;
		map.computeIfPresent("8", (string, integer) -> string.length() + integer);
		assert Objects.isNull(map.get("8"));

		assert map.get("1") == 6;
		assert map.get("2") == 3;
		assert map.get("3") == 3;
		assert map.get("4") == 5;

		assert map.get("5") == 5;
		assert map.get("6") == 1;
		assert map.get("7") == 1;
		assert Objects.isNull(map.get("8"));
		System.out.println(map);
		System.out.println(map.put("8", 1));
		System.out.println(map);
		System.out.println(map.put("8", 2));
		System.out.println(map);

	}

	public static void mutable() {
		List<StringBuilder> messages = Arrays.asList(new StringBuilder(), new StringBuilder());
		messages.stream().filter(sb -> sb.length() == 0).forEach(s -> s.append("helloworld"));
		messages.forEach(s -> {
			s.insert(5, ",");
			System.out.println(s);
		});
	}

	public static void interfacing() throws IOException {
		Samurai jack = new Samurai() {
			@Override
			public String katana() {
				return "my katana";
			}
		};

		//jack.fidelity(); не компилится
	}

	public static void ioStreams() throws FileNotFoundException {
		List<String> strings = new BufferedReader(new FileReader("заметки")).lines().limit(10).collect(Collectors.toList());
		System.out.println(strings);
		List<String> wrapped = new BufferedReader(new InputStreamReader(new BufferedInputStream(new CharacterWrapperStream(new BufferedReader(new FileReader("заметки")))), Charset.forName("UTF-8")))
				.lines().limit(10).collect(Collectors.toList());
		System.out.println(wrapped);
		assert strings.size() == wrapped.size() : MessageFormat.format("strings: {0}, wrapped: {1}", strings.size(), wrapped.size());
		for (int i = 0; i < strings.size(); ++i) {
			assert strings.get(i).equals(wrapped.get(i)) : MessageFormat.format("strings: {0}, wrapped: {1}", strings.get(i), wrapped.get(i));
		}
		assert false;
	}

	public static void deque() {
		ArrayDeque<Integer> deque = new ArrayDeque<>();
		deque.add(2);
		deque.add(3);
		deque.add(4);
		deque.offer(5);
		deque.push(6);
		System.out.println(deque); //[6, 2, 3, 4, 5]
		assert deque.remove() == 6;
//			System.out.println(deque.remove());//6
		assert deque.pop() == 2;
		assert deque.poll() == 3;
		assert deque.peek() == 4;
		assert deque.element() == 4;

		System.out.println("Trying empty");
		ArrayDeque<Integer> empty = new ArrayDeque<>();
//		System.out.println(empty.remove()); //error
//			System.out.println(empty.pop()); //error
		assert null == empty.poll(); //null
		assert null == empty.peek(); //null
//		System.out.println(empty.element()); //error
	}

	public static void streams() {
		Integer reduce = Stream.iterate(1, x -> ++x)
				.limit(11)
				.peek(System.out::println)
				.map(Object::toString)
				.reduce(0,
						(s1, s2) -> s1 + s2.length(),
						(c1, c2) -> c1 + c2);
		System.out.println("result: " + reduce);
	}

	public static void stream() {
		Stream.iterate(1, x -> x++)
				.forEach(System.out::println);
	}

	public static void duration() {
		String d = Duration.ofDays(1).toString();
		String p = Period.ofDays(1).toString();
		System.out.println("Duration: " + d + ", Period: " + p);
	}

	public static void fingers() {
		System.out.println("Hello, vim");
		Finger pinky = Finger.PINKY;
		for (Finger finger : Finger.values()) {
			System.out.println(finger + " " + finger.countFalangs());
		}
	}

	enum Finger {
		THUMB(2), INDEX, MIDDLE, RING, PINKY;
		private final int falangs;

		Finger() {
			falangs = 3;
		}

		Finger(int falangs) {
			this.falangs = falangs;
		}

		public int countFalangs() {
			return falangs;
		}
	}

}

class Person {
	private final String name;

	public Person(String name) {
		this.name = name;
	}

	String getName() {
		return name;
	}

	public String toString() {
		return name;
	}
}

class Erochin extends Person {
	public Erochin(String name) {
		super(name);
	}
}

class Sychev extends Person implements Comparable<Erochin> {
	public Sychev(String name) {
		super(name);
	}

	//	@Override
	public int compareTo(Sychev other) {
		return this.getName().compareTo(other.getName());
	}

	@Override
	public int compareTo(Erochin other) {
		return -9000;
	}
}

class Morozov {
	private int money;

	private Morozov() {
		money = 7;
	}

	public int showMoney() {
		return money;
	}

	class Pavlic extends Morozov {
		public Pavlic() {
			super();
			money = 1;
		}
	}

	public static Morozov getSon() {
		Morozov father = new Morozov();
		System.out.println("father's money before son:" + father.showMoney());
		Pavlic pavlic = father.new Pavlic();
		System.out.println("father's money now:" + father.showMoney());
		return pavlic;
	}
}

class IsItFurry {
	static interface Mammal {
	}

	static class Furry implements Mammal {
	}

	static class Chipmunk extends Furry {
	}

	public static void furryCheck() {
		Chipmunk c = new Chipmunk();
		Mammal m = c;
		Furry f = c;
		int result = 0;
		if (c instanceof Mammal) result += 1;
		if (c instanceof Furry) result += 2;
		if (null instanceof Chipmunk) result += 4;
		System.out.println(result);
	}
}
