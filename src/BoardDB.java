import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BoardDB {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.print("명령어를 입력해주세요 : ");
			String cmd = sc.nextLine();
			
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

			
			if(cmd.equals("list")) {
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
			} else if (cmd.equals("add")) {
				System.out.print("제목 : ");
				String title = sc.nextLine();
				System.out.print("내용 : ");
				String body = sc.nextLine();
				
				String sql2 = " INSERT INTO article SET title = ?, `body` = ?, nickname = '홍길동', hit = 10";
				PreparedStatement pstmt2 = conn.prepareStatement(sql2);
				
				pstmt2.setString(1, "dfdf");
				pstmt2.setString(2, "kkkk");			
				pstmt2.executeUpdate();
			}
		}

	}

}
