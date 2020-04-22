package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Article;
import com.example.demo.form.ArticleForm;
import com.example.demo.form.CommentForm;
import com.example.demo.service.ArticleService;
import com.example.demo.service.CommentService;

/**
 * 掲示板を操作するコントローラ.
 * 
 * @author masashi.nose
 *
 */
@Controller
@RequestMapping("")
public class ArticleController {

	@Autowired
	private ArticleService articleService;

	@Autowired
	private CommentService commentService;

	@ModelAttribute
	public ArticleForm setUpArticleForm() {
		return new ArticleForm();
	}

	@ModelAttribute
	public CommentForm setUpCommentForm() {
		return new CommentForm();
	}

	/**
	 * 記事一覧画面を表示します.
	 * 
	 * @param model リクエストスコープ
	 * @return　記事一覧画面
	 */
	public String index(Model model) {
		List<Article> articleList = articleService.showArticleList();
		model.addAttribute("articleList", articleList);

		return "article_list";

	}

}
