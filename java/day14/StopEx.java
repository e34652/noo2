package feb.day14;

public class StopEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PrintThread pt = new PrintThread();
		pt.start();
		
		try {
			System.out.println("zz");
			Thread.sleep(1000);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("zz");
		pt.setStop(true);
	}

}
