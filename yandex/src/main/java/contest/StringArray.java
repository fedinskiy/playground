package contest;

import java.io.*;
import java.util.Arrays;
import java.util.Objects;

public class StringArray {

	public static final int MAX_LENGTH = String.valueOf(Integer.MIN_VALUE).length();
	public static final byte[] newLine = new byte[]{'\n'};
	byte[] lastValue=new byte[]{'x'};//невозможное значение
	byte[] current=new byte[MAX_LENGTH];
	private OutputStream writer;

	public StringArray(OutputStream writer) throws IOException {
		this.writer = writer;
	}

	public static void main(String[] args) {
		try {
			new StringArray(new FileOutputStream("output.txt")).processFile("input.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void processFile(String name) throws IOException {
		try (FileInputStream reader=new FileInputStream(name)) {
			int size = readInteger(reader);
			if(size == 0) {
				System.out.println();
			} else {
				process(reader, size);
			}

		}
	}

	public static int readInteger(InputStream reader) throws IOException {
		final char[] LINE = new char[MAX_LENGTH];
		int index=0;
		int current=reader.read();
		if (current==-1){
			return 0;
		}
		while('\n'!=current&&current!=-1){
			LINE[index++]= (char) current;
			current=reader.read();
		}
		return Integer.parseInt(new String(Arrays.copyOf(LINE, index)));
	}

	// Я выполню твое заветное желание. Я покажу тебе код без субоптимальности.
	private void process(FileInputStream reader, int size) throws IOException {
		int index;
		boolean valueChanged;

		for(int i=0;i<=size;i++){
			int curValue=reader.read();
			if (curValue==-1){
				return;
			}
			valueChanged=false;
			index=-1;
			while('\n'!=curValue&&curValue!=-1){
				current[++index]= (byte) curValue;
				if(!valueChanged) {
					if (index > (lastValue.length - 1)) {
						valueChanged = true;
					} else {
						valueChanged = (current[index] != lastValue[index]);
					}
				}
				curValue=reader.read();
			}
//			if(index!=(lastValue.length-1)){
//				valueChanged=true;
//			}
			if(valueChanged||index!=(lastValue.length-1)){
				lastValue=Arrays.copyOf(current,index+1);
				printValue(lastValue);
			}

		}
	}

	private void printValue(byte[] lastValue) throws IOException {
//		System.out.println(lastValue);
//		for(int ch:lastValue){
//			writer.write(ch);
//		}
		writer.write(lastValue);
		writer.write(newLine);
		writer.flush();
	}
}
