package feb.day21;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class WriteEx3 {

	public static void main(String[] args) {
		try (OutputStream os = new FileOutputStream("c:/temp/test3.db");) {
			//Stream이 붙으면 바이트 단위 통신
			byte[] array = {10,20,30,40,50};
			
			os.write(array, 1, 3);
			
			os.flush(); // 버퍼에 잔류중인 모든 바이트를 출력 

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}