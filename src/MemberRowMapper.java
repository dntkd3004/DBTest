import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberRowMapper implements RowMapper <Member> {

	@Override
	public Member getRow(ResultSet rs) throws SQLException {
		
		int id = rs.getInt("id");
		String loginId = rs.getString("loginId");
		String loginPw = rs.getString("loginPw");
		String nickname = rs.getString("nick");
		String regDate = rs.getString("regDate");

		Member member= new Member();
		member.setLoginId(loginId);
		member.setLoginPw(loginPw);
		member.setNickname(nickname);
		member.setId(id);
		member.setRegDate(regDate);
		
		return member;
	}
}
