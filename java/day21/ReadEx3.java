package feb.day21;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ReadEx3 {

	public static void main(String[] args) {
		try(InputStream is = new FileInputStream("c:/temp/test2.db");){
			
			byte[]buffer = new byte[100];
			while(true) {
				int cnt = is.read(buffer); // 불러온 값을 buffer로 받음 
				if(cnt == -1) {// 파일의 끝 = -1
					break;
				}
				for(int i = 0; i < cnt; i++) {
					System.out.println(buffer[i]);
				}
			}
			
		}catch(IOException e) {
			e.printStackTrace();
		}

	}

}
