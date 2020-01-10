package ru.fedinskiy;

import org.junit.Test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Random;

public class IoTest {
	@Test
	public void listing() throws IOException {
		final Path testBase = Paths.get("target/test_area");
		final Path source = testBase.resolve("source").toAbsolutePath();
		final Path target = testBase.resolve("target").toAbsolutePath();
		Files.createDirectories(source);
		Files.createDirectories(target);
		System.out.println(target);
		for (String name : Arrays.asList("one", "two", "three")) {
			try (BufferedWriter writer = Files.newBufferedWriter(source.resolve(name + ".txt"))) {
				writer.write(name + ":" + new Random().nextLong() + " " + LocalDateTime.now());
			}
		}
//		Files.list(source).forEach(System.out::println);
		Files.list(source).forEach(path -> copy(path, target));
		clear(testBase);
	}

	private void copy(Path oldName, Path toDir) {
		try {
			final Path fileName = oldName.getFileName();
			final Path newPath = toDir.resolve(fileName);
			System.out.println("Copying " + oldName + " to " + newPath);
			Files.copy(oldName, newPath);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	private void clear(Path path) throws IOException {
		Files.walkFileTree(path, new Remover());
	}

	private class Remover implements FileVisitor<Path> {
		@Override
		public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
			Files.delete(file);
			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
			return FileVisitResult.TERMINATE;
		}

		@Override
		public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
			Files.delete(dir);
			return FileVisitResult.CONTINUE;
		}
	}
}
