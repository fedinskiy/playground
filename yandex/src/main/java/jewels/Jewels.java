package jewels;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Jewels {
	public static void main(String[] args) throws IOException {
		Path path = Paths.get("input.txt");
		System.out.println(path.toAbsolutePath());
		List<String> lines = Files.readAllLines(path);
		System.out.println(run(lines.get(0),lines.get(1)));
	}

	public static int run(String jewels, String stones) {
		int result=0;
		for(char stone: stones.toCharArray()){
			for (char jewel: jewels.toCharArray()){
				if(stone==jewel){
					result++;
					break;
				}
			}
		}
		return result;
	}
}
