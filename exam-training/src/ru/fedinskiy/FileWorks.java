package ru.fedinskiy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileWorks {

	public static void main(String[] args) throws IOException {
		Path path = Paths.get(".");
		System.out.println(path.toRealPath());
		Files.list(path).map(Path::getFileName).forEach(System.out::println);
		Stream<Path> stream = Files.find(path, 3,(p, a) -> p.getFileName().toString().endsWith(".txt"));
		List<String> paths = stream.map(Path::getFileName).map(Path::toString).collect(Collectors.toList());
		System.out.println(paths);
		paths.replaceAll(str->str.substring(3));
		System.out.println(paths);
	}
}
