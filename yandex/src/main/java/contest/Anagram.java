package contest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Anagram {
	private static final char ASCII_OFFSET=97;
	private final String first;
	private final String second;

	public Anagram(InputStreamReader reader) throws IOException {
		try(BufferedReader buffer = new BufferedReader(reader)) {
			this.first = buffer.readLine();
			this.second = buffer.readLine();
		}
	}

	public Anagram(String first, String second) throws IOException {
			this.first = first;
			this.second = second;
	}
	public static void main(String[] args) throws IOException {
		try(FileReader reader = new FileReader("input.txt")) {
			System.out.println(new Anagram(reader).process());
		}
	}

	public int process() {
		int[] firstLetters=processString(first);
		int[] secondLetters=processString(second);
		return Arrays.equals(firstLetters,secondLetters)
				?1
				:0;
	}

	public  static int[] processString(String source) {
		int[] result=new int[26];
		for (char c : source.toCharArray()) {
			int index = c - ASCII_OFFSET;
			result[index]=result[index]+1;
		}
		return result;
	}

}
