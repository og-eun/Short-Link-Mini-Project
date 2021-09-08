package com.test.short_link.dao;

import com.test.short_link.domain.ShortLink;
import com.test.short_link.domain.dto.AliasCustomDto;
import com.test.short_link.domain.dto.ShortLinkDto;
import com.test.short_link.domain.dto.ShortLinkListDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShortLinkMapper {

    void insertShortLink(ShortLinkDto shortLinkDto);

    String selectUrlByShortId(String shortId);

    ShortLink selectShortLinkById(String shortId);

    List<ShortLink> selectShortLinkList(ShortLinkListDto shortLinkListDto);

    int isExistShortLinkById(String shortId);

    void updateShortId(AliasCustomDto aliasCustomDto);

    void deleteShortLinkById(String shortId);

}
