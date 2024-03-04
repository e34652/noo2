package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.Board;

public class BoardDao {

	private static Connection conn;
	private static BoardDao dao = new BoardDao();

	private BoardDao() {
	} // 생성자

	public static BoardDao getInstance() {
		BoardDao.getConnection();
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

	// 페이징 할 때 필요한 총 게시글 수
	public int selectCount() {
		String sql = "select count(*) from board";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				return rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;

	}

	// BoardList 페이징

	public ArrayList<Board> selectPage(int startRow, int size) {
		ArrayList<Board> list = new ArrayList<>();
		String sql = "SELECT * FROM (SELECT rownum as rnum, b.num, b.title, b.content, b.regtime, b.hits, b.memberno, m.id, m.email, m.name FROM (SELECT * FROM board ORDER BY num DESC) b JOIN member m ON b.memberno = m.memberno) WHERE rnum BETWEEN 1 AND 5";
//		"select b.*, m.name from board b left join member m on b.memberno = m.memberno order by num desc;"; 

		// String sql = "SELECT * FROM (SELECT ROWNUM AS rnum, b.*, m.name FROM board b LEFT JOIN member m ON b.memberno = m.memberno ORDER BY num DESC) WHERE rnum BETWEEN ? AND ?;";
		// String sql = "select * from board order by num desc limit ?,?"; // 오라클은 여기만수정
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, size);
			
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Board board = new Board(rs.getInt("num"), rs.getString("title"), rs.getString("content"),
						rs.getString("regtime"), rs.getInt("hits"), rs.getInt("memberno"), rs.getString("name"));
				list.add(board);
				System.out.println(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	//
	public ArrayList<Board> selectList() {
		ArrayList<Board> list = new ArrayList<>();
		String sql = "select b.*, m.name from board b left join member m on b.memberno = m.memberno order by num desc;";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Board board = new Board(rs.getInt("NUM"), rs.getString("TITLE"), rs.getString("CONTENT"),
						rs.getString("REGTIME"), rs.getInt("HITS"), rs.getInt("MEMBERNO"), rs.getString("NAME"));
				list.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 게시글을 조회하기 위해 불러오기
	public Board selectOne(int num, boolean inc) {
		Board board = null;
		String sql = "select b.*, m.name from board b left join member m on b.memberno = m.memberno where num = ? order by num desc";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				board = new Board(rs.getInt("num"), rs.getString("title"), rs.getString("content"),
						rs.getString("regtime"), rs.getInt("hits"), rs.getInt("memberno"), rs.getString("name"));

			}
			if (inc) {
				pstmt.executeUpdate("update board set hits=hits+1 where num=" + num);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return board;

	}

	// 게시글 삭제
	public int delete(int num) {
		int result = 0;
		try (PreparedStatement pstmt = conn.prepareStatement("delete from board where num=" + num);) {
			// 쿼리 실행
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 게시글 작성
	public int insert(Board board, String memberId) {
		String sql = "insert into board(num, title, content, regtime, hits, memberno) values (SEQ_BOARD.nextval,'?','?',TO_DATE(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'),0,1;";
		try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
			// 현재 시간 얻기 mysql = now() / oracle = sysdate
//	        String curTime = LocalDate.now() + " " + 
//	                         LocalTime.now().toString().substring(0, 8);

			// 쿼리 실행
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setInt(3, board.getMemberno());
			return pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int update(Board board) {
		String sql = "update board set title=?, content=?, regtime=TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') where num=?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql);) {

			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setInt(3, board.getNum());
			return pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}