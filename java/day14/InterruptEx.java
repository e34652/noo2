package feb.day14;

public class InterruptEx {

	public static void main(String[] args) {
		PrintThread2 pt2 = new PrintThread2();
		pt2.start();
		
		try {
			Thread.sleep(1000); // sleep과 interrupt를 이용해 몇초간 쓰레드가 실행되다가 멈출지 제어 
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pt2.interrupt();
	}

}
