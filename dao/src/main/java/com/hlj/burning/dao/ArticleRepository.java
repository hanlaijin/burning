package com.hlj.burning.dao;

import com.hlj.burning.po.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
