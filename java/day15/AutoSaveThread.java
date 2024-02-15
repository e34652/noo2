package feb.day15;

public class AutoSaveThread extends Thread {
	public void save() {
		System.out.println("저장");
	}

	@Override
	public void run() {
		while (true) {
			save();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				break;
			}
			
		}
	}
}
