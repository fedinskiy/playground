import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Tasks {

	public static void main(String[] args) throws IOException {
		ArrayDeque<Integer> input = Files.lines(Paths.get("input.txt"))
				.map(Integer::parseInt)
				.collect(Collectors.toCollection(ArrayDeque::new));
		int taskCount = input.pop(); //n, число задач
		int processorCount = input.pop(); //k, число процессов

		ArrayDeque<Integer> tasks = input.stream()
				.limit(taskCount) //мало ли, что еще в конце файла понаписали
				.sorted(Comparator.reverseOrder())//сначала считаем жирные задачи
				.collect(Collectors.toCollection(ArrayDeque::new));
		List<Integer> history = IntStream.iterate(0, i -> i).limit(taskCount).boxed().collect(Collectors.toList());

		System.out.println(taskCount);
		System.out.println(processorCount);
		System.out.println(history);
		System.out.println(tasks);

		Integer consuming;
		int requiredBattery = 0;
		int currentProcessorIndex = 0;
		while (Objects.nonNull(consuming = tasks.poll())) {
			Integer processedTasks = history.get(currentProcessorIndex);
			requiredBattery += (3 * processedTasks + 1) * consuming;
			history.set(currentProcessorIndex, processedTasks + 1);
			currentProcessorIndex = (currentProcessorIndex == processorCount-1) ? 0 : ++currentProcessorIndex;
		}


		System.out.println(requiredBattery);
	}
}
