package com.example.demo.form;

/**
 * コメント投稿フォームからリクエストパラメータを受け取るフォーム.
 * 
 * @author masashi.nose
 *
 */
public class CommentForm {
	/** 記事ID */
	private Integer articleId;
	/** コメント者名 */
	private String name;
	/** コメント内容 */
	private String content;

	@Override
	public String toString() {
		return "CommentForm [articleId=" + articleId + ", name=" + name + ", content=" + content + "]";
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
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
