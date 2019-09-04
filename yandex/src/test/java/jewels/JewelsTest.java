package jewels;



import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class JewelsTest {

	private Jewels jewels = new Jewels();

	@Test
	public void runTest() {
		Assert.assertEquals(2, Jewels.run("a", "aabb"));
	}

	@Test
	public void runITest() throws IOException {
		Jewels.main(null);
	}
}