package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Comment;
import com.example.demo.repository.CommentRepository;

/**
 * commentsテーブルの業務処理をするサービスクラス.
 * 
 * @author masashi.nose
 *
 */
@Service
@Transactional
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;

	/**
	 * commentsテーブルにコメントをインサートします.
	 * 
	 * @param comment commentオブジェクト
	 * @return　ID自動採番されたcommentオブジェクト
	 */
	public Comment insert(Comment comment) {
		return commentRepository.insert(comment);

	}
}
