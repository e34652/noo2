package feb.day21;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ReadEx2 {

	public static void main(String[] args) {
		try (InputStream is = new FileInputStream("c:/temp/test3.db");) {

			byte[] buffer = new byte[5];

			int num = is.read(buffer, 2, 3);
			if (num != -1) {
				for (int i = 0; i < buffer.length; i++) {
					System.out.println(buffer[i]);
				}
			}

		} catch (

		IOException e) {
			e.printStackTrace();
		}

	}

}
