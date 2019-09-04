package contest;

import org.junit.Assert;
import org.junit.Test;

public class MergeTest {

	@Test
	public void print() {
		String[] test = {"a", "b", "c", "d"};
		Merge merge = new Merge(test);
		Assert.assertEquals("a b c d", merge.print(test));
	}

	@Test
	public void lineCreation() {
		Merge.Line line = new Merge.Line("6 2 26 64 88 96 96"+'\n');
		Assert.assertEquals(6, line.getLength());
//		Assert.assertArrayEquals("2 26 64 88 96 96".toCharArray(), line.getContent());
		Assert.assertArrayEquals("2".toCharArray(), line.next());
		Assert.assertArrayEquals("26".toCharArray(), line.next());
		Assert.assertArrayEquals("64".toCharArray(), line.next());
		Assert.assertArrayEquals("88".toCharArray(), line.next());
		Assert.assertArrayEquals("96".toCharArray(), line.next());
		Assert.assertArrayEquals("96".toCharArray(), line.next());
		Assert.assertNull(line.next());
	}

	@Test
	public void comparing(){
		Assert.assertFalse(Merge.secondIsLess(new char[]{'5'},new char[]{'1','0'}));
		Assert.assertTrue(Merge.secondIsLess(new char[]{'5'},new char[]{'1'}));
		Assert.assertFalse(Merge.secondIsLess(new char[]{'5'},new char[]{'5'}));
	}

	@Test
	public void join() {
		String[] test = {"6 2 26 64 88 96 96\n",
		"4 8 20 65 86\n",
		"7 1 4 16 42 58 61 69\n",
		"1 84\n"};
		Merge merge = new Merge(test);
//		"2 26 64 88 96 96 8 20 65 86 1 4 16 42 58 61 69 84 ";
		Assert.assertEquals("1", new String (merge.nextJoin()));
		Assert.assertEquals("2", new String (merge.nextJoin()));
		Assert.assertEquals("4", new String (merge.nextJoin()));
		Assert.assertEquals("8", new String (merge.nextJoin()));
		Assert.assertEquals("16", new String (merge.nextJoin()));
		Assert.assertEquals("20", new String (merge.nextJoin()));
		Assert.assertEquals("26", new String (merge.nextJoin()));
		Assert.assertEquals("42", new String (merge.nextJoin()));
		Assert.assertEquals("58", new String (merge.nextJoin()));
		Assert.assertEquals("61", new String (merge.nextJoin()));
		Assert.assertEquals("64", new String (merge.nextJoin()));
		Assert.assertEquals("65", new String (merge.nextJoin()));
		Assert.assertEquals("69", new String (merge.nextJoin()));
		Assert.assertEquals("84", new String (merge.nextJoin()));
		Assert.assertEquals("86", new String (merge.nextJoin()));
		Assert.assertEquals("88", new String (merge.nextJoin()));
		Assert.assertEquals("96", new String (merge.nextJoin()));
		Assert.assertEquals("96", new String (merge.nextJoin()));

	}
	@Test
	public void joinZero() {
		String[] test = {"6 2 26 64 88 96 96\n",
				"4 8 20 65 86\n",
				"7 1 4 16 42 58 61 69\n",
				"1 84\n"};
		Merge merge = new Merge(test);
//		"2 26 64 88 96 96 8 20 65 86 1 4 16 42 58 61 69 84 ";
		Assert.assertEquals("1", new String (merge.nextJoin()));
		Assert.assertEquals("2", new String (merge.nextJoin()));
		Assert.assertEquals("4", new String (merge.nextJoin()));
		Assert.assertEquals("8", new String (merge.nextJoin()));
		Assert.assertEquals("16", new String (merge.nextJoin()));
		Assert.assertEquals("20", new String (merge.nextJoin()));
		Assert.assertEquals("26", new String (merge.nextJoin()));
		Assert.assertEquals("42", new String (merge.nextJoin()));
		Assert.assertEquals("58", new String (merge.nextJoin()));
		Assert.assertEquals("61", new String (merge.nextJoin()));
		Assert.assertEquals("64", new String (merge.nextJoin()));
		Assert.assertEquals("65", new String (merge.nextJoin()));
		Assert.assertEquals("69", new String (merge.nextJoin()));
		Assert.assertEquals("84", new String (merge.nextJoin()));
		Assert.assertEquals("86", new String (merge.nextJoin()));
		Assert.assertEquals("88", new String (merge.nextJoin()));
		Assert.assertEquals("96", new String (merge.nextJoin()));
		Assert.assertEquals("96", new String (merge.nextJoin()));

	}
}