
public class Member {
	private int id;
	private String LoginId;
	private String LoginPw;
	private String Nickname;
	private String regDate;
	
	public Member() {
		
	}
	
	public Member(int id, String loginId, String loginPw, String nickname, String regDate) {
		this.id = id;
		LoginId = loginId;
		LoginPw = loginPw;
		Nickname = nickname;
		this.regDate = regDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLoginId() {
		return LoginId;
	}

	public void setLoginId(String loginId) {
		LoginId = loginId;
	}

	public String getLoginPw() {
		return LoginPw;
	}

	public void setLoginPw(String loginPw) {
		LoginPw = loginPw;
	}

	public String getNickname() {
		return Nickname;
	}

	public void setNickname(String nickname) {
		Nickname = nickname;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public static void insertMember(Member m) {
		m.setId(no);
		no++;
		m.setRegDate(Util.getCurrentDate());

		members.add(m);
	}
}
