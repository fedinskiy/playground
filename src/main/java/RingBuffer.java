public interface RingBuffer<T> {
	T get();
	T read();
	boolean write(T val);

	default boolean add(T test) {
		return write(test);
	}

	default T element() {
		return read();
	}

	default T poll() {
		return get();
	}
}
