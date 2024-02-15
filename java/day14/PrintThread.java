package feb.day14;

public class PrintThread extends Thread {
	private boolean stop;

	
	public void setStop(boolean stop) {
		this.stop = stop;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(!stop) { // while의 무한 루프를 멈추게 하여 제어
			System.out.println("실행 중");
		}
		System.out.println("자원정리");
		System.out.println("실행종료");
	}

	
}
