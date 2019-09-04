package contest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.Arrays;

public class Merge {
	public static final int ARRAY_LEN = String.valueOf(1024 * 10).length();
	public final Line[] values;
	public final char[][] latest;
	public final int size;
//	public int last=-1;

	public Merge(InputStreamReader reader) throws IOException {
		try (BufferedReader buffer = new BufferedReader(reader)) {
			size = Integer.parseInt(buffer.readLine());
			values = new Line[size];
			latest= new char[size][];
			for (int i = 0; i < size; i++) {
				values[i] = new Line(buffer.readLine());
				latest[i]=values[i].next();
			}
		}
	}

	public Merge(String[] values) {
		size = values.length;
		this.values = new Line[size];
		latest= new char[size][];
		for (int i = 0; i < size; i++) {
			this.values[i] = new Line(values[i]);
			this.latest[i]=this.values[i].next();
		}
	}

	public static void main(String[] args) throws IOException {
		Merge merge;
		try (FileReader reader = new FileReader("input.txt")) {
			merge = new Merge(reader);
		}
		if (merge.size==0) return;
		char[] value=merge.nextJoin();
		char[] next;
		while(null!=(next=merge.nextJoin())){
			System.out.print(value);
			System.out.print(' ');
			value=next;
		}
		System.out.print(value);
	}

	public String print(String[] joined) {
		int lastIndex = joined.length - 1;
		StringBuilder result = new StringBuilder(joined.length);

		for (int i = 0; i < lastIndex; i++) {
			result.append(joined[i]);
			result.append(' ');
		}
		result.append(joined[lastIndex]);
		return result.toString();
	}

	public char[] nextJoin() {
		int minIndex=0;
		for (int i = 1; i < size; i++) {
			if (secondIsLess(latest[minIndex], latest[i])){
				minIndex=i;
			}
		}

		char[] result = latest[minIndex];
		latest[minIndex]=values[minIndex].next();
		return result;
	}

	public static boolean secondIsLess(char[] first, char[] second) {
		if(first == null) {
			return true;
		}
		if(second== null) {
			return false;
		}
		if(second.length<first.length){ //больше знаков — больше значение
			return true;
		} else if (second.length>first.length){
			return false;
		} else {
			for (int i = 0; i < second.length; i++) {
				int result=first[i]-second[i];
				if (result==0) continue;
				return result>0;
			}
		}
		return false;
	}

	public static class Line {
		private int length;
		private final char[] content;
		private int offset;

		public Line(String content) {
			char[] arr = content.toCharArray();

			char ch;
			char len[] = new char[ARRAY_LEN];
			int index = -1;
			while (' ' != (ch = arr[++index])) {
				len[index] = ch;
			}
			String lengthAsString = new String(Arrays.copyOf(len, index));
			this.length = Integer.parseInt(lengthAsString);
			this.content = arr;
//			this.content = Arrays.copyOfRange(arr,
//			                                  lengthAsString.length()+1,//+ пробел
//			                                  arr.length );
			offset = lengthAsString.length() + 1;
		}

		public char[] next() {
			try {
				if (length <= 0) return null;
				char result[] = new char[3];//макс-100
				int index = 0;
				char ch;
				while (' ' != (ch = content[offset++])) {
					result[index++] = ch;
					if (offset>=content.length||content[offset] == '\n') break;
				}
				length--;
				return Arrays.copyOf(result, index);
			}catch (ArrayIndexOutOfBoundsException ex){
				throw new IllegalArgumentException(MessageFormat.format("{0} {1} {2}",
				                                                        length,
				                                                        Arrays.toString(content),
				                                                        offset),
				                                   ex);
			}
		}

		public int getLength() {
			return length;
		}

		public char[] getContent() {
			return content;
		}
	}
}
