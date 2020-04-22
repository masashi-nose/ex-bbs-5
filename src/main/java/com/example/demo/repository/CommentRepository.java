package com.example.demo.repository;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Comment;

/**
 * commentsテーブルを操作するリポジトリ.
 * 
 * @author masashi.nose
 *
 */
@Repository
public class CommentRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private SimpleJdbcInsert insert;

	@PostConstruct
	public void init() {
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert((JdbcTemplate) template.getJdbcOperations());
		SimpleJdbcInsert withTableName = simpleJdbcInsert.usingGeneratedKeyColumns("comments");
		insert = withTableName.usingGeneratedKeyColumns("id");

	}

	/**
	 * commentsテーブルにインサートします.
	 * 
	 * @param comment commentオブジェクト
	 * @return　ID自動採番されたcommentオブジェクト
	 */
	public Comment insert(Comment comment) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(comment);

		if (comment.getId() == null) {
			Number key = insert.executeAndReturnKey(param);
			comment.setId(key.intValue());
		}
		return comment;
	}
}
