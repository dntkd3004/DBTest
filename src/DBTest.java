import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		//=======================================DB 세팅=========================================
		
		// 드라이버 정보
		String driver = "com.mysql.cj.jdbc.Driver";
		// DBMS 주소
		String url = "jdbc:mysql://localhost:3306/t1?serverTimezone=UTC";
		
		// 사용자 계정
		String user = "sbsst";
		// 사용자 비밀번호
		String pass = "sbs123414";
		Class.forName(driver); // Driver 세팅
		Connection conn = DriverManager.getConnection(url, user, pass); // DBMS 선택 -> 담당자(Connection) 부여받음

		//=========================================조회===========================================
		String sql = "SELECT * FROM article";
		
		PreparedStatement pstmt = conn.prepareStatement(sql); // PreparedStatment 통해서 SQL 세팅
		
		ResultSet rs = pstmt.executeQuery(); // 조회 결과가 있는 경우
		//pstmt.executeUpdate(); // 조회 결과가 없는 경우
		
		while(rs.next()) {
			String title = rs.getString("title");
			int id = rs.getInt("id");
			String body = rs.getString("body");
			String nickname = rs.getString("nickname");
			int hit = rs.getInt("hit");
			System.out.println(id + " " + title + " " + body + " " + nickname + " " + hit);			
		}
		
		//=========================================삽입===========================================
		String sql2 = " INSERT INTO article SET title = ?, `body` = ?, nickname = '홍길동', hit = 10";
		PreparedStatement pstmt2 = conn.prepareStatement(sql2);
		
		pstmt2.setString(1, "dfdf");
		pstmt2.setString(2, "kkkk");			
		pstmt2.executeUpdate();
		
				
	}
}
