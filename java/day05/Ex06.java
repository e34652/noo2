package feb.day05;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ex06 {

	public static void main(String[] args) {
		mainClock();
		Thread th = new Thread() {
			
			@Override
			public void run() {
				for (int i = 0; i < 5; i++) {
					LocalDateTime localDateTime = LocalDateTime.now();
					String localDateTimeFormat1 = localDateTime
							.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));
					System.out.println(localDateTimeFormat1);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("time out");
			}
		};
	}

	public static void mainClock() {
		System.out.println(LocalDateTime.now()); // 날짜 표시할 때 이걸 사용함 << 권고사항, 성능이 제일 좋음

		for (int i = 0; i < 5; i++) {
			LocalDateTime localDateTime = LocalDateTime.now();
			String localDateTimeFormat1 = localDateTime
					.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));
			System.out.println(localDateTimeFormat1);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("time out");

	}

}
