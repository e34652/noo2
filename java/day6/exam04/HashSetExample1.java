package feb.day6.exam04;

import java.util.*;

public class HashSetExample1 {
	public static void main(String[] args) {
		Set<String> set = new HashSet<String>();
		
		set.add("Java");
		set.add("JDBC");
		set.add("Servlet/JSP");
		set.add("Java");
		set.add("iBATIS");
		
		//set에는 인덱스가 없어 데이터가 정렬되어있지 않아 데이터를 특정할 수 없어 전체를 순회해야함
		//순차적 자료 순회1
		Iterator<String> it = set.iterator();
		while(it.hasNext()) {
			String str = it.next();
			System.out.println(str);
		}
		
		//순차적 자료 순회2 
		for(String str : set) {
			System.out.println(str);
		}
		
		
		int size = set.size();
		System.out.println("총 객체수: " + size);
		
		Iterator<String> iterator = set.iterator();
		while(iterator.hasNext()) {
			String element = iterator.next();
			System.out.println("\t" + element);
		}
		
		set.remove("JDBC");
		set.remove("iBATIS");
		
		System.out.println("총 객체수: " + set.size());
		
		for(String element : set) {
			System.out.println("\t" + element);
		}
		
		set.clear();		
		if(set.isEmpty()) { System.out.println("비어 있음"); }
	}
}

