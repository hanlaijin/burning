package com.hlj.burning.dao;

import com.hlj.burning.po.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Token getTokenByAppId(String appId);
}
