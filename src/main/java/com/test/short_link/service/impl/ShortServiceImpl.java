package com.test.short_link.service.impl;

import com.test.short_link.dao.ShortLinkMapper;
import com.test.short_link.domain.ShortLink;
import com.test.short_link.domain.dto.AliasCustomDto;
import com.test.short_link.domain.dto.ShortLinkDto;
import com.test.short_link.domain.dto.ShortLinkListDto;
import com.test.short_link.service.ShortLinkService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ShortServiceImpl implements ShortLinkService {

    private final ShortLinkMapper shortLinkMapper;

    @Override
    public String convertUrlToShort() {
        // 5자리 alphaNumeric 발생
        String shortId = generateUUID(5);

        // 있는지 체크
        if (isExistShortLinkById(shortId))
            convertUrlToShort();

        return shortId;
    }

    @Override
    public String selectUrlByShortId(String shortId) {
        return shortLinkMapper.selectUrlByShortId(shortId);
    }

    @Override
    public void insertShortLink(ShortLinkDto shortLinkDto) {
        shortLinkMapper.insertShortLink(shortLinkDto);
    }

    @Override
    public List<ShortLink> selectShortLinkList(ShortLinkListDto shortLinkListDto) {
        // 페이징 처리
        shortLinkListDto.calculate();

        return shortLinkMapper.selectShortLinkList(shortLinkListDto);
    }

    @Override
    public ShortLink selectShortLinkById(String shortId) {
        return shortLinkMapper.selectShortLinkById(shortId);
    }

    @Override
    public boolean isExistShortLinkById(String shortId) {
        return 0 < shortLinkMapper.isExistShortLinkById(shortId);
    }

    @Override
    public void customizeShortId(AliasCustomDto aliasCustomDto) {
        shortLinkMapper.updateShortId(aliasCustomDto);
    }

    @Override
    public void deleteShortLinkById(String shortId) {
        shortLinkMapper.deleteShortLinkById(shortId);
    }

    /**
     * UUID 를 통해 random alphaNumeric 생성 & 입력된 자리수 까지 자르기
     */
    private String generateUUID(int length) {
        return UUID.randomUUID().toString().replace("-","").substring(0, length).toLowerCase();
    }

}
