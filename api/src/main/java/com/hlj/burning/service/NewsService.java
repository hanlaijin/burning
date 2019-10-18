package com.hlj.burning.service;

import com.google.common.collect.Lists;
import com.hlj.burning.dao.ArticleRepository;
import com.hlj.burning.po.Article;
import com.hlj.burning.wechat.dto.AddNewsRequest;
import com.hlj.burning.wechat.dto.News;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class NewsService {

    @Autowired
    private ArticleRepository articleRepository;

    public AddNewsRequest getNews() {
        Article article = articleRepository.getOne(1L);
        return build(article);
    }

    private AddNewsRequest build(Article article) {
        AddNewsRequest request = new AddNewsRequest();
        List<News> newsList = Lists.newArrayList();

        News news = new News();
        news.setTitle("");
        news.setThumbMediaId("");
        news.setAuthor("");
        news.setShowCoverPic(1);
        news.setContent("");
        news.setContentSourceUrl("");
        news.setNeedOpenComment(1);
        news.setOnlyFansCanComment(1);

        newsList.add(news);
        request.setArticles(newsList);
        return request;
    }
}
