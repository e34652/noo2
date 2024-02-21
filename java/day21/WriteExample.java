package feb.day21;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class WriteExample {

	public static void main(String[] args) {
		try(Writer writer = new FileWriter("c:/temp/test7.txt")){
			char a = 'A';
			char b = 'B';
			char c = 'C';
			
			writer.write(a);
			writer.write(b);
			writer.write(c);
			
		}catch(IOException e) {
			e.printStackTrace();
		}

	}

}
