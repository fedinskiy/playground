import org.junit.Assert;
import org.junit.Test;

public class RingBufferTest {

	@Test
	public void testGetEmpty() {
		RingBuffer<String> buffer = getBuffer(1);
		Assert.assertNull(buffer.element());
		Assert.assertNull(buffer.poll());
	}

	private <T> SpaciousRingBuffer<T> getBuffer(int size) {
		return new SpaciousRingBuffer<>(size);
	}

	@Test
	public void testGet() {
		RingBuffer<String> buffer = getBuffer(1);
		String test = "some string";

		Assert.assertTrue(buffer.add(test));
		System.out.println(buffer);
		Assert.assertEquals(test, buffer.element());
		Assert.assertEquals(test, buffer.poll());
	}

	@Test
	public void testReadAfterEnd() {
		RingBuffer<String> buffer = getBuffer(1);
		String test = "some string";

		Assert.assertTrue(buffer.add(test));
		System.out.println(buffer);
		Assert.assertEquals(test, buffer.poll());
		System.out.println(buffer);
		Assert.assertNull(buffer.poll());
	}

	@Test
	public void testSeveral() {
		RingBuffer<Integer> buffer = getBuffer(2);
		Integer first = 1;
		Integer second = 2;

		Assert.assertTrue(buffer.add(first));
		System.out.println(buffer);
		Assert.assertTrue(buffer.add(second));
		System.out.println(buffer);
		Assert.assertEquals(first, buffer.poll());
		System.out.println(buffer);
		Assert.assertEquals(second, buffer.poll());
	}

	@Test
	public void testSeveralFromMiddle() {
		RingBuffer<Integer> buffer = getBuffer(4);
		Integer first = 1;
		Integer second = 2;

		Assert.assertTrue(buffer.add(first));
		System.out.println(buffer);
		Assert.assertTrue(buffer.add(second));
		System.out.println(buffer);
		Assert.assertEquals(first, buffer.poll());
		System.out.println(buffer);
		Assert.assertEquals(second, buffer.poll());
	}

	@Test
	public void testOverflow() {
		RingBuffer<Integer> buffer = getBuffer(2);
		Integer first = 1;
		Integer second = 2;
		Integer third = 3;

		Assert.assertTrue(buffer.add(first));
		Assert.assertTrue(buffer.add(second));
		System.out.println(buffer);
		boolean success = buffer.add(third);
		System.out.println(buffer);
		Assert.assertFalse(success);

		Assert.assertEquals(first, buffer.poll());
		Assert.assertEquals(second, buffer.poll());
	}

	@Test
	public void testRing() {
		RingBuffer<Integer> buffer = getBuffer(2);

		Integer first = 1;
		Integer second = 2;
		Integer third = 3;
		Assert.assertTrue(buffer.add(first));
		System.out.println(buffer);
		Assert.assertTrue(buffer.add(second));
		System.out.println(buffer);
		Assert.assertFalse(buffer.add(third));
		Assert.assertEquals(first, buffer.poll());
		System.out.println(buffer);
		Assert.assertTrue(buffer.add(third));
		Assert.assertEquals(second, buffer.poll());
		Assert.assertEquals(third, buffer.poll());
	}
}