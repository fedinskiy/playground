package contest;

import org.junit.Test;

import java.io.IOException;

public class ArrayTest {

	@Test
	public void processFile() throws IOException {
		Array.processFile("target/test-classes/arrayinput.txt");
//		System.out.println(Arrays.toString(result));
//		Assert.assertArrayEquals(new int[]{2, 4, 8}, result);
	}

	@Test
	public void processSecondFile() throws IOException {
		Array.processFile("target/test-classes/arrayinput2.txt");
//		System.out.println(Arrays.toString(result));
//		Assert.assertArrayEquals(new int[]{2, 8}, result);
	}

	@Test
	public void processZero() throws IOException {
		new BetterArray(System.out).processFile("target/test-classes/arrayinput0.txt");
	}

	@Test
	public void betterProcessFile() throws IOException {
		new BetterArray(System.out).processFile("target/test-classes/arrayinput.txt");
//		System.out.println(Arrays.toString(result));
//		Assert.assertArrayEquals(new int[]{2, 4, 8}, result);
	}

	@Test
	public void betterProcessSecondFile() throws IOException {
		new BetterArray(System.out).processFile("target/test-classes/arrayinput2.txt");
//		System.out.println(Arrays.toString(result));
//		Assert.assertArrayEquals(new int[]{2, 8}, result);
	}

	@Test
	public void stringProcessZero() throws IOException {
		new StringArray(System.out).processFile("target/test-classes/arrayinput0.txt");
	}

	@Test
	public void stringProcessFile() throws IOException {
		new StringArray(System.out).processFile("target/test-classes/arrayinput.txt");
	}

	@Test
	public void stringProcessSecondFile() throws IOException {
		new StringArray(System.out).processFile("target/test-classes/arrayinput2.txt");
	}
}