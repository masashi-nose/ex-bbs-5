package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Article;
import com.example.demo.domain.Comment;

/**
 * 
 * articlesテーブルを操作するリポジトリ.
 * 
 * @author masashi.nose
 *
 */
@Repository
public class ArticleRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private SimpleJdbcInsert insert;

	private static final ResultSetExtractor<List<Article>> ARTICLE_RESULT_SET_EXTRACTOR = (rs) -> {
		List<Article> articleList = new ArrayList<>();
		List<Comment> commentList = new ArrayList<>();

		int articleIdToCheck = 0;

		while (rs.next()) {
			int currentArticleId = rs.getInt("article_id");

			if (currentArticleId != articleIdToCheck) {
				Article article = new Article();
				article.setId(rs.getInt("article_id"));
				article.setName(rs.getString("article_name"));
				article.setContent(rs.getString("article_content"));
				article.setCommentList(commentList);
				articleList.add(article);

			}

			if (rs.getInt("comment_id") != 0) {
				Comment comment = new Comment();
				comment.setId(rs.getInt("comment_id"));
				comment.setName(rs.getString("comment_name"));
				comment.setContent(rs.getString("comment_content"));
				comment.setArticleId(rs.getInt("comment_article_id"));
				commentList.add(comment);

			}

			articleIdToCheck = currentArticleId;

		}

		return articleList;

	};

	/**
	 * 記事とコメントを全件検索します.
	 * 
	 * @return 記事一覧情報
	 */
	public List<Article> findAll() {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT a.id article_id, a.name article_name, a.content article_content, c.id comment_id, c.name comment_name, c.content comment_content, c.article_id comment_article_id ");
		sql.append("FROM articles a LEFT OUTER JOIN comments c on a.id = c.article_id ");
		sql.append("ORDER BY a.id desc");

		List<Article> articleList = template.query(sql.toString(), ARTICLE_RESULT_SET_EXTRACTOR);

		if (articleList == null) {
			return null;
		}

		return articleList;
	}

	@PostConstruct
	public void init() {
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert((JdbcTemplate) template.getJdbcOperations());
		SimpleJdbcInsert withTableName = simpleJdbcInsert.withTableName("articles");
		insert = withTableName.usingGeneratedKeyColumns("id");
	}

	/**
	 * articlesテーブルに記事情報をインサートします.
	 * 
	 * @param article 記事オブジェクト
	 * @return　ID自動採番された記事情報
	 */
	public Article insert(Article article) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(article);

		if (article.getId() == null) {
			Number key = insert.executeAndReturnKey(param);
			article.setId(key.intValue());
		}

		return article;
	}

}
