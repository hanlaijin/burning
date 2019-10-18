package com.hlj.burning.controller;

import com.hlj.burning.po.Token;
import com.hlj.burning.service.NewsService;
import com.hlj.burning.service.TokenService;
import com.hlj.burning.wechat.config.WeChatConfig;
import com.hlj.burning.wechat.dto.AddNewsRequest;
import com.hlj.burning.wechat.util.WeChatUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RestController
public class WeChatController {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private NewsService newsService;

    @GetMapping(value = "/getToken", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String token() {
        Token token = tokenService.getAccessToken(WeChatConfig.APP_ID);
        System.out.println(token);
        return token.getAccessToken();
    }

    @RequestMapping(value = "/wx", method = {RequestMethod.GET, RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String wx(HttpServletRequest request, HttpServletResponse response) {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        System.out.println("------- echo str=" + echostr);
        if (!WeChatUtil.checkSignature(signature, timestamp, nonce)) {
            return "";
        }
        return echostr;
    }

    @GetMapping(value = "/news", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void news() throws IOException {
        Token token = tokenService.getAccessToken(WeChatConfig.APP_ID);
        AddNewsRequest request = newsService.getNews();
        WeChatUtil.addNews(token.getAccessToken(), request);
    }
}
