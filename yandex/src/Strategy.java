import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Strategy {

	public static void main(String[] args) throws IOException {
		ArrayDeque<String> input = Files.lines(Paths.get("input_test1.txt"))
				.collect(Collectors.toCollection(ArrayDeque::new));
		int roundCount = Integer.parseInt(input.pop()); //k, число партий
		String blockStart;
		LinkedList<Round> rounds = new LinkedList<>();
		while (Objects.nonNull(blockStart = input.poll())) {
			int countryCount = Integer.valueOf(blockStart);
			int[] sizes = Stream.of(input.poll())
					.map(s -> s.split(" "))
					.flatMap(Stream::of)
					.mapToInt(Integer::valueOf)
					.toArray();
			rounds.add(new Round(countryCount, sizes));
		}
		System.out.println(rounds);
		rounds.stream().map(Round::getWinner).forEach(System.out::println);
	}
}

class Round {
	private static final IntPredicate splittable = size -> size > 1;
	public static final int MAX_COUNTRY_SIZE = 50;
	final int countryCount;
	final int[] countrySize;
	private static final List<Boolean> splittingWinner = fillWinners();
	final Player winner;


	public Round(int countryCount, int[] countrySize) {
		this.countryCount = countryCount;
		this.countrySize = countrySize;
		winner = calculateWinner();
	}

	private static List<Boolean> fillWinners() {
		final Predicate<Integer> winnableNumberOfSteps = i -> i % 2 == 1;
		final Function<Integer, Boolean> nullableNumberOfSteps = obj -> Objects.isNull(obj)
				? null
				: winnableNumberOfSteps.test(obj);
//		List<Boolean> result = new ArrayList<>(MAX_COUNTRY_SIZE + 1);
		List<Integer> steps = new ArrayList<>(MAX_COUNTRY_SIZE + 1);

		steps.add(0, 0);
		steps.add(1, 0);
		steps.add(2, 0);
		steps.add(3, 1);
		steps.add(4, 2);

/*
		result.add(0,null);
		result.add(1,false);
		//TODO числа ниже этой строки надо рассчитывать, а не забивать
		result.add(2,false);
		result.add(3,true);
		result.add(4,false);
*/
		for (int i = 5; i <= MAX_COUNTRY_SIZE; i++) {
			steps.add(null);
		}
		return steps.stream()
				.map(nullableNumberOfSteps)
				.collect(Collectors.toList());
//		return result;

	}

	public Player getWinner() {
		return winner;
	}

	private Player calculateWinner() {
		List<Integer> splittable =
				IntStream.of(countrySize)
						.filter(Round.splittable)
						.sorted()
						.boxed()
						.collect(Collectors.toList());
		return playRound(Player.FIRST, splittable);

	}

	public static Boolean getWinner(int countrySize) {
		for (int i = 1; i < countrySize / 2; i++) {

		}
		return splittingWinner.get(countrySize);
	}

	private Player playRound(Player current, List<Integer> countries) {
		if (countries.isEmpty())
			return current.another(); //не можешь сходить — проигрываешь

//		Integer smallest = countries.remove(0);

		Optional<Boolean> result = countries.stream()
				.map(Round::getWinner)
				.filter(Objects::nonNull)
				.reduce(Boolean::logicalOr);
		return result
				.map(iswinner -> iswinner ? current : current.another())
				.orElse(Player.DUNNO);
		/*if (countries.isEmpty()) {
			Boolean knownResult = splittingWinner.get(smallest);
			if (Objects.isNull(knownResult)) {
				//todo надо думать
				return Player.DUNNO; //todo этого остаться не должно
			}

			return knownResult ? current : current.another();
		}

		return Player.DUNNO; //todo этого остаться не должно*/
	}

	enum Player {
		DUNNO, FIRST, SECOND;

		public Player another() {
			switch (this) {
				case FIRST:
					return SECOND;
				case SECOND:
					return FIRST;
				default:
					return DUNNO;
			}
		}

		@Override
		public String toString() {
			switch (this) {
				case FIRST:
					return "Игрок 1";
				case SECOND:
					return "Игрок 2";
				default:
					return "Игрок ХЗ";
			}
		}
	}

	@Override
	public String toString() {
		return "Round{" +
				"countryCount=" + countryCount +
				", countrySize=" + Arrays.toString(countrySize) +
				", winner=" + winner +
				'}';
	}
}