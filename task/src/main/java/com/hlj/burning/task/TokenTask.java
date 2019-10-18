package com.hlj.burning.task;

import com.hlj.burning.dao.TokenRepository;
import com.hlj.burning.po.Token;
import com.hlj.burning.wechat.config.WeChatConfig;
import com.hlj.burning.wechat.util.WeChatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TokenTask {

    @Autowired
    private TokenRepository tokenRepository;

    @Scheduled(fixedRate = 90 * 60 * 1000)
    public void refreshAccessToken() {
        System.out.println("----- refreshAccessToken -----");
        try {
            String token = WeChatUtil.refreshAccessToken();
            Token t = tokenRepository.getTokenByAppId(WeChatConfig.APP_ID);
            t.setAccessToken(token);
            System.out.println(t);
            tokenRepository.save(t);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
