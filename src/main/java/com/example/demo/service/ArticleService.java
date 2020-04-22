package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Article;
import com.example.demo.repository.ArticleRepository;

/**
 * articlesテーブル関連の業務処理をするサービスクラス.
 * 
 * @author masashi.nose
 *
 */
@Service
@Transactional
public class ArticleService {

	@Autowired
	private ArticleRepository articleRepository;

	/**
	 * 記事一覧を表示します.記事に付随してコメントも表示します.
	 * 
	 * 
	 * @return 記事+コメント一覧情報
	 */
	public List<Article> showArticleList() {
		return articleRepository.findAll();

	}

	/**
	 * 記事を投稿します.
	 * 
	 * @param article 記事オブジェクト
	 * @return ID自動採番記事オブジェクト
	 */
	public Article insert(Article article) {
		return articleRepository.insert(article);

	}
}
