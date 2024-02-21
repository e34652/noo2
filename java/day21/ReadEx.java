package feb.day21;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ReadEx {

	public static void main(String[] args) {
		try(InputStream is = new FileInputStream("c:/temp/test2.db");){
			
			while(true) {
				int data = is.read();
				if(data == -1) {// 파일의 끝 = -1
					break;
				}
				System.out.println(data);
			}
			
		}catch(IOException e) {
			e.printStackTrace();
		}

	}

}
