package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

		public int selectCount() {  // board 테이블의 총 건수를 리턴함
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
		
		public Member selectMember(String id) {
			Member member = null;
			String sql = "select * from Member where id = ?";
			PreparedStatement pstmt;

			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				
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
		
		public ArrayList<Member> selectPage(int startRow, int size) {
			ArrayList<Member> list = new ArrayList<>();
			String sql = "SELECT * FROM (SELECT a.*, ROWNUM as rnum FROM (SELECT * FROM member ORDER BY memberno DESC) a) WHERE ROWNUM >= ? and rnum <= ?;";
			PreparedStatement pstmt;
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, size);
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

			String sql = "insert into Member (id, email, name) values (?, ?, ?)";

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
			String sql = "update Member set email = ?, name = ? where id= ?";
			try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
				
				pstmt.setString(1, member.getEmail());
				pstmt.setString(2, member.getName());
				pstmt.setString(3, member.getId());

				return pstmt.executeUpdate();
				                                           
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
		}
		
//		public int del(int num) {
//			String sql = "delete from Member where num = ?";
//
//			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
//				pstmt.setInt(1, num);
//				
//				return pstmt.executeUpdate();
//
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			return 0;
//		}
	}

