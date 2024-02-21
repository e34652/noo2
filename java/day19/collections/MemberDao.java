package feb.day19.collections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

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
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project1", "root", "mysql");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}

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
		
		public Member select(String id) {
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
		public HashMap<String, Member> selectMembers() {
			HashMap<String, Member> map = new HashMap<>();
			String sql = "select * from member";
			PreparedStatement pstmt;
			try {
				pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
				Member member = new Member(rs.getString("id"), rs.getString("email"), rs.getString("name"));
				map.put(member.getId(), member);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			return map;

		}
	}

