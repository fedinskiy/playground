public interface RingBuffer<T> {

	boolean add(T test);

	T element();

	T poll();
}
