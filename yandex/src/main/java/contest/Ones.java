package contest;

import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;

import static java.io.StreamTokenizer.TT_NUMBER;

public class Ones {
	public static void main(String[] args) throws IOException {
//		Stream<String> lines = Files.lines(Paths.get("input.txt"));
		try (FileReader reader = new FileReader("input.txt")) {
			StreamTokenizer tokenizer = new StreamTokenizer(reader);
			tokenizer.parseNumbers();
			tokenizer.eolIsSignificant(false);
			int token = tokenizer.nextToken();
			if (token != TT_NUMBER) {
				System.out.println("0");
			} else if (tokenizer.nval == 0) {
				System.out.println("0");
			} else {
				System.out.println(process(tokenizer));
			}

		}

	}

	private static Integer process(StreamTokenizer lines) throws IOException {
		int longest = 0;
		int current = 0;
		while (TT_NUMBER == lines.nextToken()) {
			final double line = lines.nval;
			if(1==line){
				if(++current>longest){
					longest=current;
				}
			} else {
				current=0;
			}
		}
		return longest;
	}
}
