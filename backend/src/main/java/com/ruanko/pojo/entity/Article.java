package com.ruanko.pojo.entity;

public class Article {

	/**
	 * 编号
	 */
	private int id;
	
	/**
	 * 标题
	 */
	private String title;
	
	/**
	 * 作者
	 */
	private String author;
	
	/**
	 * 内容
	 */
	private String content;


	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Article{" +
				"id=" + id +
				", title='" + title + '\'' +
				", author='" + author + '\'' +
				", content='" + content + '\'' +
				'}';
	}
}
