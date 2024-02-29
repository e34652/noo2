package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;

import dto.Board;
import dto.Member;

public class MemberDao {

		private static Connection conn;
		private static MemberDao dao = new MemberDao();

		private MemberDao() {
		}

		public static MemberDao getInstance() {
			MemberDao.getConnection();
			return dao;
		}

		private static void getConnection() {

			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}

		}
		
//		private static void getConnection() {
//
//			try {
//				Class.forName("com.mysql.cj.jdbc.Driver");
//				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project1", "root", "mysql");
//			} catch (ClassNotFoundException | SQLException e) {
//				e.printStackTrace();
//			}
//
//		}

		public int selectCount() {  
			// board 테이블의 총 건수를 리턴함
			String sql = "select count(*) from member";
			PreparedStatement pstmt;
			try {
				pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				
				if (rs.next()) {
					return rs.getInt(1); // 첫번째 열의 Int값을 리턴
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return 0;
			
		}
		
		public Member selectForLogin(String id, String email) { 
			// 로그인 시 입력한 id email을 받아 일치하는 행 리턴
			Member member = null;
			String sql = "select * from Member where id = ? and email = ?";
			PreparedStatement pstmt;

			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setString(2, email);
				ResultSet rs = pstmt.executeQuery();
			
				if (rs.next()) {
					member = new Member(rs.getString("id"), rs.getString("email"),
							rs.getString("name"));
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return member;
		}
		
		public Member selectMember(int memberno) {
			Member member = null;
			String sql = "select * from Member where memberno = ?";
			PreparedStatement pstmt;

			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, memberno);
				
				ResultSet rs = pstmt.executeQuery();

				if (rs.next()) {
					member = new Member(rs.getInt("memberno"), rs.getString("id"), rs.getString("email"), rs.getString("name"));
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return member;
		}
		
		public ArrayList<Member> selectPage(int startRow, int size) {
			ArrayList<Member> list = new ArrayList<>();
			String sql = "SELECT * FROM MEMBER"; 
			//(SELECT ROWNUM AS ROW_NUM, T.* FROM (SELECT * FROM MEMBER ORDER BY MEMBERNO DESC) T WHERE ROWNUM <= ?) WHERE ROW_NUM >= ?;
			PreparedStatement pstmt;
			try {
				pstmt = conn.prepareStatement(sql);
//				pstmt.setInt(1, size);
//				pstmt.setInt(2, startRow);
				
				ResultSet rs = pstmt.executeQuery();
				
				while (rs.next()) {
				Member member = new Member(rs.getInt("memberno"), rs.getString("id"), rs.getString("email"),
							rs.getString("name"));
				list.add(member); 
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return list;
		}

		public int insert(Member member) {

			String sql = "insert into Member (memberno, id, email, name) values (SEQ_MEMBER.nextval, ?, ?, ?)";

			try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
				pstmt.setString(1, member.getId());
				pstmt.setString(2, member.getEmail());
				pstmt.setString(3, member.getName());
				

				return pstmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
		}
		
		public int update(Member member) {
			String sql = "update Member set id = ?, email = ?, name = ? where memberno = ?";
			try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
		
				pstmt.setString(1, member.getId());
				pstmt.setString(2, member.getEmail());
				pstmt.setString(3, member.getName());
				pstmt.setInt(4, member.getMemberno());

				return pstmt.executeUpdate();
				                                           
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
		}
		
		public int delete(int num) {
			String sql = "delete from member where memberno = ?";
					
			try (PreparedStatement pstmt = conn.prepareStatement(sql);){
					pstmt.setInt(1, num);
					
					return pstmt.executeUpdate();
			     
			    } catch(Exception e) {
			        e.printStackTrace();
			    }
			return 0;
		}
		
	
	}

