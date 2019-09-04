package contest;

import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;

import static java.io.StreamTokenizer.TT_NUMBER;

public class Array {
	public static void main(String[] args) throws IOException {
		processFile("input.txt");
//		for (int i : processFile("input.txt")) {
//			System.out.println(i);
//		}
	}

	public static void processFile(String name) throws IOException {
//		System.out.println(Paths.get(name).toAbsolutePath());
		try (FileReader reader = new FileReader(name)) {

			StreamTokenizer tokenizer = new StreamTokenizer(reader);
			tokenizer.parseNumbers();
			tokenizer.eolIsSignificant(false);
			int token = tokenizer.nextToken();
			if (token != TT_NUMBER) {
				System.out.println();
			} else if (tokenizer.nval == 0) {
				System.out.println();
			} else {
				process(tokenizer, tokenizer.nval);
			}

		}
	}

	private static void process(StreamTokenizer tokenizer, double nval) throws IOException {
//		int[] result=new int[(int) nval];
		int current;
		int lastValue;
		{
			tokenizer.nextToken();
			lastValue = (int) tokenizer.nval;//если мы тут, то первый элемент существует
			System.out.println(lastValue);
		}
		while (TT_NUMBER==tokenizer.nextToken()){
			current = (int) tokenizer.nval;
			if(current!=lastValue){
				lastValue=current;
				System.out.println(lastValue);
			}
		}
//		return Arrays.copyOf(result,lastIndex+1);
	}
}
