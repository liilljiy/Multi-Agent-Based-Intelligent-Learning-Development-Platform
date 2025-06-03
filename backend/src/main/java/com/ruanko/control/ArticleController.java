package com.ruanko.control;

import com.ruanko.pojo.entity.Article;
import com.ruanko.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @RequestMapping(value = "/toAddNews",method = RequestMethod.GET)
    public String toAddNews(){
        return "article/addNews";
    }

    @RequestMapping(value = "/articleAdd",method = RequestMethod.POST)
    public String articleAdd(Article article){
        articleService.insert(article);
        return "index";
    }

    @RequestMapping(value = "/toArticleDel",method = RequestMethod.GET)
    public String toArticleDel(Model model,Integer id){
        Article article=articleService.findByPrimaryKey(id);
        model.addAttribute("article",article);
        return "article/deleteDetail";
    }

    @RequestMapping(value = "/articleDel",method = RequestMethod.POST)
    public String articleDel(Integer id){
        articleService.deleteByPrimaryKey(id);
        return "index";
    }

    @RequestMapping("/articleShow")
    public String articleShow(Model model,Integer id){
        Article article=articleService.findByPrimaryKey(id);
        model.addAttribute("article",article);
        return "article/showDetail";
    }

    @RequestMapping("/articleShowList")
    public String articleShowList(Model model){
        List<Article> articles=articleService.findAll();
        model.addAttribute("articles",articles);
        System.out.println("ArticleController.articleShowList" + "----article值= " + articles);
        return "article/showNews";
    }

    @RequestMapping("/toArticleUpdate")
    public String toArticleUpdate(Model model,Integer id){
        Article article=articleService.findByPrimaryKey(id);
        model.addAttribute("article",article);
        return "article/updateDetail";
    }

    @RequestMapping("/articleUpdate")
    public String articleUpdate(Article article){
        articleService.update(article);
        return "index";
    }
}