package feb.day13;

public class MusicRunnable implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 3; i++) {
			Thread.currentThread().setName("Music");
			System.out.println("음악 재생" + Thread.currentThread().getName());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
