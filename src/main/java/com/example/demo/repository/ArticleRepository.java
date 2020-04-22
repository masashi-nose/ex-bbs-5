package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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

	private static final ResultSetExtractor<List<Article>> ARTICLE_RESULT_SET_EXTRACTOR = (rs) -> {
		List<Article> articleList = new ArrayList<>();
		List<Comment> commentList = new ArrayList<>();

		int articleIdToCheck = 0;

		while (rs.next()) {
			int currentArticleId = rs.getInt("id");

			if (currentArticleId != articleIdToCheck) {
				Article article = new Article();
				article.setId(rs.getInt("id"));
				article.setName(rs.getString("name"));
				article.setContent(rs.getString("content"));
				article.setCommentList(commentList);
				articleList.add(article);

			}

			if (rs.getInt("id") != 0) {
				Comment comment = new Comment();
				comment.setId(rs.getInt("id"));
				comment.setName(rs.getString("name"));
				comment.setContent(rs.getString("content"));
				comment.setArticleId(rs.getInt("article_id"));
				commentList.add(comment);

			}

			articleIdToCheck = currentArticleId;

		}

		return articleList;

	};
	
	
	
	

}
