package feb.day21;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class WriteEx {

	public static void main(String[] args) {
		try (OutputStream os = new FileOutputStream("c:/temp/test1.db");) {
			//Stream이 붙으면 바이트 단위 통신
			byte a = 10;
			byte b = 20;
			byte c = 30;

			os.write(a); // 1byte씩 출력
			os.write(b);
			os.write(c);
			
			os.flush(); // 버퍼에 잔류중인 모든 바이트를 출력 

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}