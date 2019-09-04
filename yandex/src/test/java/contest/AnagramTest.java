package contest;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.*;

public class AnagramTest {

	@Test
	public void process() throws IOException {
		Assert.assertEquals(1, new Anagram("qiu", "iuq").process());
		Assert.assertEquals(0, new Anagram("zprl", "zprc").process());
	}

	@Test
	public void processString() {
		System.out.println(Arrays.toString(Anagram.processString("azz")));
	}
}