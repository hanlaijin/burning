package com.hlj.burning.service;

import com.hlj.burning.dao.TokenRepository;
import com.hlj.burning.po.Token;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TokenService {
    @Autowired
    private TokenRepository tokenRepository;

    public Token getAccessToken(String appId) {
        Token token = tokenRepository.getTokenByAppId(appId);
        return token;
    }

    public void saveAccessToken(Token token) {
        tokenRepository.save(token);
    }
}
