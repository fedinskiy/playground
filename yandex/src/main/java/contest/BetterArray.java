package contest;

import java.io.*;
import java.util.Objects;

public class BetterArray {
	private final PrintWriter writer;

	public BetterArray(OutputStream writer) throws IOException {
		this.writer = new PrintWriter(writer);
	}

	public static void main(String[] args) {
		try {
			new BetterArray(new FileOutputStream("output.txt")).processFile("input.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void processFile(String name) throws IOException {
		try (BufferedReader reader=new BufferedReader(new FileReader(name))) {
			int size = Integer.parseInt(reader.readLine());
			if(size== 0) {
				System.out.println();
			} else {
				process(reader, size);
			}

		}
	}

	private void process(BufferedReader reader, double nval) throws IOException {
		int current;
		int lastValue;
		String line;
		{
			lastValue = Integer.parseInt(reader.readLine());//если мы тут, то первый элемент существует
			printValue(lastValue);
		}
		while (Objects.nonNull(line=reader.readLine())){
			current = Integer.parseInt(line);
			if(current!=lastValue){
				lastValue=current;
				printValue(lastValue);
			}
		}
	}

	private void printValue(int lastValue) throws IOException {
//		System.out.println("call for "+lastValue);
//		writer.write(lastValue+48);
		writer.print(toBuf(lastValue));
		writer.write('\n');
		writer.flush();
//		System.out.println(lastValue);
	}

	final static int [] sizeTable = { 9, 99, 999, 9999, 99999, 999999, 9999999,
			99999999, 999999999, Integer.MAX_VALUE };
	static void getChars(int i, int index, char[] buf) {
		int q, r;
		int charPos = index;
		char sign = 0;

		if (i < 0) {
			sign = '-';
			i = -i;
		}

		// Generate two digits per iteration
		while (i >= 65536) {
			q = i / 100;
			// really: r = i - (q * 100);
			r = i - ((q << 6) + (q << 5) + (q << 2));
			i = q;
			buf [--charPos] = DigitOnes[r];
			buf [--charPos] = DigitTens[r];
		}

		// Fall thru to fast mode for smaller numbers
		// assert(i <= 65536, i);
		for (;;) {
			q = (i * 52429) >>> (16+3);
			r = i - ((q << 3) + (q << 1));  // r = i-(q*10) ...
			buf [--charPos] = digits [r];
			i = q;
			if (i == 0) break;
		}
		if (sign != 0) {
			buf [--charPos] = sign;
		}
	}

	final static char[] digits = {
			'0' , '1' , '2' , '3' , '4' , '5' ,
			'6' , '7' , '8' , '9' , 'a' , 'b' ,
			'c' , 'd' , 'e' , 'f' , 'g' , 'h' ,
			'i' , 'j' , 'k' , 'l' , 'm' , 'n' ,
			'o' , 'p' , 'q' , 'r' , 's' , 't' ,
			'u' , 'v' , 'w' , 'x' , 'y' , 'z'
	};
	final static char [] DigitTens = {
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'1', '1', '1', '1', '1', '1', '1', '1', '1', '1',
			'2', '2', '2', '2', '2', '2', '2', '2', '2', '2',
			'3', '3', '3', '3', '3', '3', '3', '3', '3', '3',
			'4', '4', '4', '4', '4', '4', '4', '4', '4', '4',
			'5', '5', '5', '5', '5', '5', '5', '5', '5', '5',
			'6', '6', '6', '6', '6', '6', '6', '6', '6', '6',
			'7', '7', '7', '7', '7', '7', '7', '7', '7', '7',
			'8', '8', '8', '8', '8', '8', '8', '8', '8', '8',
			'9', '9', '9', '9', '9', '9', '9', '9', '9', '9',
	} ;

	final static char [] DigitOnes = {
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
	} ;

	public static char[] toBuf(int i){
		if (i == Integer.MIN_VALUE)
			return new char[]{'-','2','1','4','7','4','8','3','6','4','8'};
		int size = (i < 0) ? stringSize(-i) + 1 : stringSize(i);
		char[] buf = new char[size];
		getChars(i, size, buf);
		return buf;
	}
	static int stringSize(int x) {
		for (int i=0; ; i++)
			if (x <= sizeTable[i])
				return i+1;
	}
}
