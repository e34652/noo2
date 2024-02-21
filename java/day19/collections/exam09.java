package feb.day19.collections;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class exam09 {
	

	public static void main(String[] args) {
		MemberDao dao = MemberDao.getInstance();
		Map<String, Member> map = dao.selectMembers();
		Scanner scan = new Scanner(System.in);

		while (true) {
			System.out.println("아이디와 비밀번호 입력");
			System.out.println("아이디: ");
			String id = scan.nextLine();
			System.out.println("비밀번호: ");
			String pw = scan.nextLine();
			System.out.println();

			if (map.containsKey(id)) {
				if (map.get(id).getEmail().equals(pw)) {
					System.out.println(map.get(id).getEmail());
					System.out.println("로그인 됨");
					break;
				} else {
					System.out.println("로그인 실패");
				}
//			} else {
//				System.out.println("입력한 아이디가 존재하지 않음");
//			}
			}
		}
	}
}
