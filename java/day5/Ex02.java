package feb.day5;

public class Ex02 {

	public static void main(String[] args) {
		Task task = new Task();
		Thread th = new Thread(task); // 다른 코어에서 실행시키겠다, 생성한 객체를 활용해 Thread를 하나 더
		th.start();
		mainTask();
	}

	public static void mainTask() {
		System.out.println("시작");
		for (int i = 0; i < 5; i++) {
			System.out.println("진행");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("끝");
	}

}
