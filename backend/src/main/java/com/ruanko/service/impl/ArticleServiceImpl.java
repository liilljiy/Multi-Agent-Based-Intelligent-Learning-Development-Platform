package com.ruanko.service.impl;

import com.ruanko.pojo.entity.Article;
import com.ruanko.mapper.ArticleMapper;
import com.ruanko.mapper.BaseMapper;
import com.ruanko.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
* Title: ArticleServiceImpl
* Description: 
* 小组操作实现类
* Version:1.0.0  

 */
@Service
public class ArticleServiceImpl extends BaseServiceImpl<Article> implements ArticleService {

	@Autowired
	private ArticleMapper articleMapper;

	@Override
	protected BaseMapper<Article> getMapper() {
		return this.articleMapper;
	}
}
