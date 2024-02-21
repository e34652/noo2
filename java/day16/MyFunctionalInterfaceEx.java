package feb.day16;

public class MyFunctionalInterfaceEx {

	public static void main(String[] args) {
		MyFunctionalInterface f1, f2;
		
		f1 = (x, y) -> x + y; //{return x + y;}; << 리턴까지도 생략 가능, 인터페이스에서 모든 정보를 다 참고함
		f2 = (x, y) -> sum(x, y); //{return sum(x, y);};, 다른 메소드를 불러와 사용 가능 
		int result = f1.method(10, 10);
		int result2 = f2.method(100, 100);
		System.out.println(result);
		System.out.println(result2);
	}
	public static int sum(int x, int y) {
		return x+y;
	}
}
