package com.test.short_link.domain;

import lombok.Data;

@Data
public class ShortLink {

    private String shortId;     // short url id

    private String url;         // 원본 url

    private String createdAt;   // 생성일

}
