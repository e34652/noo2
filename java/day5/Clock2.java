package feb.day5;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JFrame;

public class Clock2 implements Runnable {
	JFrame jf;
	
	Clock2(JFrame jf){
		this.jf = jf;
	}
	
	@Override
	public void run() {
		for(;;) {
			LocalDateTime localDateTime = LocalDateTime.now();	
			String localDateTimeFormat1 = localDateTime.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH시 mm분 ss초"));
//			System.out.println(localDateTimeFormat1);
			jf.setTitle(localDateTimeFormat1);
			
			try {
				Thread.sleep(1000);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
