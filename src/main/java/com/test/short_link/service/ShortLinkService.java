package com.test.short_link.service;

import com.test.short_link.domain.ShortLink;
import com.test.short_link.domain.dto.AliasCustomDto;
import com.test.short_link.domain.dto.ShortLinkDto;
import com.test.short_link.domain.dto.ShortLinkListDto;

import java.util.List;

public interface ShortLinkService {

    /**
     * url 을 Short Link 로 변환
     */
    String convertUrlToShort() throws Exception;

    /**
     * Short Link Id 를 url 로 변환
     */
    String selectUrlByShortId(String shortId) throws Exception;

    /**
     * 생성된 Short Link 저장
     */
    void insertShortLink(ShortLinkDto shortLinkDto);

    /**
     * Short Link List 조회
     */
    List<ShortLink> selectShortLinkList(ShortLinkListDto shortLinkListDto) throws Exception;

    /**
     * 해당되는 Short Link 조회
     */
    ShortLink selectShortLinkById(String shortId);

    /**
     * 해당 shortId 의 Link 가 존재하는지 조회
     */
    boolean isExistShortLinkById(String shortId);

    /**
     * shortId 를 입력한 aliasName 으로 변경
     */
    void customizeShortId(AliasCustomDto aliasCustomDto);

    /**
     * 해당되는 Short Link 삭제
     */
    void deleteShortLinkById(String shortId);

}
