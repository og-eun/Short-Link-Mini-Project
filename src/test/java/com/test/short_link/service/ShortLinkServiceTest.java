package com.test.short_link.service;


import com.test.short_link.domain.dto.ShortLinkDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ShortLinkServiceTest {
    private final String shortId = "xxxxx";
    private final String url = "www.google.com";

    @Autowired
    private ShortLinkService shortLinkService;
    private ShortLinkDto shortLinkDto = new ShortLinkDto();

    @BeforeEach
    void before_short_link() {
        shortLinkDto.setShortId(shortId);
        shortLinkDto.setUrl(url);
    }

    @Test
    void short_link_생성() throws Exception {
        // when
        String result = shortLinkService.convertUrlToShort();
        // then
        assertNotNull(result);
        System.out.println(result);
    }

    @Test
    void short_link_저장() {
        // when
        shortLinkService.insertShortLink(shortLinkDto);
        // then
        assertTrue(shortLinkService.isExistShortLinkById(shortLinkDto.getShortId()));
    }

    @Test
    void short_link_삭제() {
        // when
        shortLinkService.deleteShortLinkById(shortId);
        // then
        assertFalse(shortLinkService.isExistShortLinkById(shortId));
    }

}