package com.example.demo.form;

import javax.validation.constraints.NotBlank;

/**
 * 投稿フォームからリクエストパラメータを受け取るフォーム.
 * 
 * @author masashi.nose
 *
 */
public class ArticleForm {
	/** 投稿者名 */
	@NotBlank(message="名前が未入力です。")
	private String name;
	/** 投稿内容 */
	@NotBlank(message="投稿内容が未入力です。")
	private String content;

	@Override
	public String toString() {
		return "ArticleForm [name=" + name + ", content=" + content + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
