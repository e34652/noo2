package feb.day13;

public class MovieThread extends Thread {
	
	public MovieThread() {
		this.setName("Movie");
	}
	@Override
	public void run() {
		for (int i = 0; i < 3; i++) {
			System.out.println(this.getName() + "동영상 재생");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
