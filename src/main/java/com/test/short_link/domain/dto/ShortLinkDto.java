package com.test.short_link.domain.dto;

import lombok.Data;

/**
 * Short Link 등록할 때 사용
 */
@Data
public class ShortLinkDto {

    private String shortId;     // short url id

    private String url;         // 원본 url

}