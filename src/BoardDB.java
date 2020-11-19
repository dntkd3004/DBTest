import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BoardDB {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		ArticleDao articleDao = new ArticleDao();
		
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
				articleDao.getArticles();
//				String sql = "SELECT * FROM article";
//				
//				PreparedStatement pstmt = conn.prepareStatement(sql); // PreparedStatment 통해서 SQL 세팅
//				
//				ResultSet rs = pstmt.executeQuery(); // 조회 결과가 있는 경우
//				//pstmt.executeUpdate(); // 조회 결과가 없는 경우
//				
//				while(rs.next()) {
//					String title = rs.getString("title");
//					int id = rs.getInt("id");
//					String body = rs.getString("body");
//					String nickname = rs.getString("nickname");
//					int hit = rs.getInt("hit");
//					System.out.println(id + " " + title + " " + body + " " + nickname + " " + hit);			
//				}
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
			} else if (cmd.equals("update")) {
				System.out.print("번호 : ");
				int id = Integer.parseInt(sc.nextLine());
				
				String sql2 = "SELECT * FROM article Where id = ?";
				PreparedStatement pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setInt(1, id);
				
				ResultSet rs = pstmt2.executeQuery();
				
				if (rs.next()) {
					String sql = " UPDATE article SET title = ?, `body` = ? WHERE id = ?";
					PreparedStatement pstmt = conn.prepareStatement(sql);
					
					System.out.print("제목 : ");
					String title = sc.nextLine();
					System.out.print("내용 : ");
					String body = sc.nextLine();
					
					pstmt.setString(1, title);
					pstmt.setString(2, body);
					pstmt.setInt(3, id);
					
					pstmt.executeUpdate();
				} else {
					System.out.println("없는 게시물입니다.");
				}
			} else if (cmd.equals("delete")) {
				System.out.print("번호 : ");
				int id = Integer.parseInt(sc.nextLine());
				
				String sql2 = "SELECT * FROM article Where id = ?";
				PreparedStatement pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setInt(1, id);
				
				ResultSet rs = pstmt2.executeQuery();
				
				if (rs.next()) {
					String sql = " DELETE FROM article WHERE id = ?";
					PreparedStatement pstmt = conn.prepareStatement(sql);
					
					pstmt.setInt(1, id);
					
					pstmt.executeUpdate();
				} else {
					System.out.println("없는 게시물입니다.");
				}
			} else if (cmd.equals("read")) {
				System.out.print("번호 : ");
				int id = Integer.parseInt(sc.nextLine());
				
				String sql2 = "SELECT * FROM article Where id = ?";
				PreparedStatement pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setInt(1, id);
				
				ResultSet rs = pstmt2.executeQuery();
				
				if (rs.next()) {
					int id2 = rs.getInt("id");
					String title = rs.getString("title");
					String body = rs.getString("body");
					
					System.out.println("번호 : " + id2);
					System.out.println("제목 : " + title);
					System.out.println("내용 : " + body);
				} else {
					System.out.println("없는 게시물입니다.");
				}
			}
		}

	}

}
