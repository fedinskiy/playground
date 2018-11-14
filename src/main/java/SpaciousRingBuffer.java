import java.util.Arrays;

public class SpaciousRingBuffer<T> implements RingBuffer<T> {
	private T[] hold;
	private int start; //смотрит на начало реальных данных(отсюда будет прочитано следующее значение)
	private int end;//смотрит после конца реальных данных(сюда будет записано следующее значение)

	public SpaciousRingBuffer(int size) {
		this.hold = (T[]) new Object[size + 1];
		this.start = 1;
		this.end = 1;
	}

	public T read() {
		return hold[start];
	}

	public T get() {
		if (end == start) {
			return null;
		}
		return hold[incrementStart()];
	}

	public boolean write(T val) {
		if (getNext(end) == start) return false;
		hold[end] = val;
		incrementEnd();
		return true;
	}

	private int getNext(int index) {
		int result = index + 1;
		if (result > hold.length - 1) {
			result = 0;
		}
		return result;
	}

	private int incrementEnd() {
		return end = getNext(end);
	}

	private int incrementStart() {
		int next = start;
		start = getNext(start);
		return next;
	}

	@Override
	public String toString() {
		return "RingBuffer{" +
				"hold=" + Arrays.toString(hold) +
				", start=" + start +
				", end=" + end +
				'}';
	}
}
