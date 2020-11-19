import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticleDao {
	// 드라이버 정보
	String driver = "com.mysql.cj.jdbc.Driver";
	// DBMS 주소
	String url = "jdbc:mysql://localhost:3306/t1?serverTimezone=UTC";
	
	// 사용자 계정
	String user = "sbsst";
	// 사용자 비밀번호
	String pass = "sbs123414";
	
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(driver); // Driver 세팅
		Connection conn = DriverManager.getConnection(url, user, pass); // DBMS 선택 -> 담당자(Connection) 부여받음		
		return conn;
	}
	
	public getArticles() {
		String sql = "SELECT * FROM article";
		Connection conn = getConnection();
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
			
			Article article = new Article();
		}
	}
}
