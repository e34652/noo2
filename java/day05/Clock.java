package feb.day05;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Clock implements Runnable {

	@Override
	public void run() {
		for(;;) {
			LocalDateTime localDateTime = LocalDateTime.now();	
			String localDateTimeFormat1 = localDateTime.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH시 mm분 ss초"));
			System.out.println(localDateTimeFormat1);
			try {
				Thread.sleep(1000);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
