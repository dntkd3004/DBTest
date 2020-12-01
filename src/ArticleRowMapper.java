import java.sql.ResultSet;

public class ArticleRowMapper  implements RowMapper<Article> {

	public Article getRow(ResultSet rs) throws SQLException {
		
		Article article = new Article();
		
		article.setId(rs.getInt("id"));
		
		String title = rs.getString("title");
		int id = rs.getInt("id");
		String body = rs.getString("body");
		String nickname = rs.getString("nickname");
		int hit = rs.getInt("hit");

		Article article = new Article();
		article.setTitle(title);
		article.setBody(body);
		article.setNickname(nickname);
		article.setId(id);
		article.setHit(hit);

		articles.add(article);
	}
}
