package feb.day16;

import java.util.Arrays;
import java.util.List;

public class StreamEx1 {

	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(1,2,3,4,5); // 각 숫자를 list 인덱스에 삽입
		list
		.stream()
		//.filter(i->i%2==0)// i -> {return i%2==0;} << i 를 변수로 넣어 조건
		.map(i ->"i에 2를 곱한 값은 " + i * 2 + " 입니다")
		.limit(3)
		.forEach(i->System.out.println(i)); //.forEach(System.out::println); << 같은 코드, :: 연산자는 스태틱 메소드만 가능함

//		list.stream().map(i -> board.getTitle()+ "-" + board.getContent().forEach(System.out.println(i)); list 의 요소를 map 에 저장하여 뽑아내기
//		list.stream().forEach i->System.out.println(i.getTitle() + "-" + i.getContent)); board 객체에서 뽑아내기 
 
		for(Integer i : list) {
			if (i % 2 == 0) {//2의 배수 추출
				System.out.println(i);
			}
		}

	}

}
