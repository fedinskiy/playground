import java.util.Arrays;

public class LengthRingBuffer<T> implements RingBuffer<T> {
	private T[] hold;
	private int current; //смотрит на начало реальных данных(отсюда будет прочитано следующее значение)
	private int size;//число записей

	public LengthRingBuffer(int size) {
		this.hold = (T[]) new Object[size];
		this.current = 0;
		this.size = 0;
	}

	@Override
	public boolean add(T value) {
		hold[current + size++] = value;
		return true;
	}

	@Override
	public T element() {
		return hold[current];
	}

	@Override
	public T poll() {
		return hold[current++];
	}

	@Override
	public String toString() {
		return "LengthRingBuffer{" +
				"hold=" + Arrays.toString(hold) +
				", current=" + current +
				", size=" + size +
				'}';
	}
}
