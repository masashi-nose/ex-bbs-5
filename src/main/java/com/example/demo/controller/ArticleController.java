package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Article;
import com.example.demo.domain.Comment;
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
	 * @return 記事一覧画面
	 */
	@RequestMapping("/showList")
	public String index(Model model) {
		List<Article> articleList = articleService.showArticleList();
		model.addAttribute("articleList", articleList);

		return "article_list";

	}

	/**
	 * 記事投稿をします.
	 * 
	 * @param article 記事オブジェクト
	 * @return 記事一覧
	 */
	@RequestMapping("/insert")
	public String insert(@Validated ArticleForm form, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return index(model);
		}

		Article article = new Article();
		BeanUtils.copyProperties(form, article);
		articleService.insert(article);
		return "redirect:/showList";
	}

	public String insertComment(@Validated CommentForm form, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return index(model);
		}
		
		Comment comment = new Comment();
		BeanUtils.copyProperties(form, comment);
		commentService.insert(comment);
		return "redirect:/showList";
	}

}
