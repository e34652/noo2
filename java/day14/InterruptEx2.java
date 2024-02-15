package feb.day14;

public class InterruptEx2 {

	public static void main(String[] args) {
		PrintThread3 pt3 = new PrintThread3();
		pt3.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pt3.interrupt(); // interrupt를 일으켜 if문의 조건을 통해 쓰레드를 제어
	}

}
