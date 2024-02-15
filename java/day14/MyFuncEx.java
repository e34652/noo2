package feb.day14;

public class MyFuncEx {

	public static void main(String[] args) {
		MyFunc mf, mf2, mf3;
		//람다식은 해당 인터페이스에 재정의 해야하는 메소드의 수가 단 하나일 때 사용 가능
		//mf3 = () -> {}; //아래의 익명 객체 생성 과정을 간소화 시킴, 중괄호 안에는 메소드가 들어감, 메소드가 한줄짜리면 중괄호 생략 가능 
		//mf3 = () -> System.out.println("method"); << 가능
		//메소드 선언부는 컴파일러가 인터페이스를 확인하여 알 수 있으므로 생략
		//매개변수가 하나면 소괄호도 안묶어도 됨
		mf3 = x -> {System.out.println("method");};
		mf3.method(0);
				
		mf = new MyFunc() {
			
			@Override
			public void method(int x) {
			System.out.println("method");
				
			}
		};
		mf.method(1);
		
		mf2 = (x) -> {System.out.println("method");};
		mf2.method(0);
	}

}
