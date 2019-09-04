package ru.fedinskiy;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;
import java.util.function.Supplier;

public class concurrency {
	private static int counter = 0;

	public static void simpleThread() throws InterruptedException {

		new Thread(() -> {
			for (int i = 0; i < 500; i++) {
				concurrency.counter++;
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					System.exit(1);
				}
			}
		}).start();

		while (concurrency.counter < 100) {
			System.out.println("Not reached yet " + counter);
			Thread.sleep(1000); // 1 SECOND
		}
		System.out.println("Reached!");
	}

	public static void forkJoin() {

	}

	public static void executorServiceAsync() {
		try (Service service = Service.create()) {
			Future<String> future = service.submit(() -> "Printing zoo inventory");
			getResult(future).ifPresent(System.out::println);

		}
	}

	public static <T> Optional<T> getResult(Future<T> future) {
		try {
			return Optional.ofNullable(future.get());
		} catch (InterruptedException | ExecutionException e) {
			return Optional.empty();
		}
	}

	public static void executorService() {
//		ExecutorService service = null;
		try (Service service = Service.create()) {
//			service = Executors.newSingleThreadExecutor();


			System.out.println("begin");
			service.execute(() -> System.out.println("Printing zoo inventory"));
			service.execute(() -> {
						for (int i = 0; i < 3; i++)
							System.out.println("Printing record: " + i);
					}
			);
			service.execute(() -> System.out.println("Printing zoo inventory"));
			System.out.println("end");
		}
	}

	public static void main(String[] args) {
		executorServiceAsync();
	}
}

class Service implements AutoCloseable, ExecutorService {
	private final ExecutorService service;

	public Service(Supplier<ExecutorService> serviceSource) {
		service = serviceSource.get();
	}

	@Override
	public void close() {
		service.shutdown();
	}

	@Override
	public void execute(Runnable command) {
		service.execute(command);
	}

	public static Service create() {
		return new Service(Executors::newSingleThreadExecutor);
	}

	public static Service create(Supplier<ExecutorService> serviceSource) {
		return new Service(serviceSource);
	}

	@Override
	public void shutdown() {
		service.shutdown();
	}

	@Override
	public List<Runnable> shutdownNow() {
		return service.shutdownNow();
	}

	@Override
	public boolean isShutdown() {
		return service.isShutdown();
	}

	@Override
	public boolean isTerminated() {
		return service.isTerminated();
	}

	@Override
	public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
		return service.awaitTermination(timeout, unit);
	}

	@Override
	public <T> Future<T> submit(Callable<T> task) {
		return service.submit(task);
	}

	@Override
	public <T> Future<T> submit(Runnable task, T result) {
		return service.submit(task, result);
	}

	@Override
	public Future<?> submit(Runnable task) {
		return service.submit(task);
	}

	@Override
	public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
		return service.invokeAll(tasks);
	}

	@Override
	public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException {
		return service.invokeAll(tasks, timeout, unit);
	}

	@Override
	public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
		return service.invokeAny(tasks);
	}

	@Override
	public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
		return service.invokeAny(tasks, timeout, unit);
	}
}