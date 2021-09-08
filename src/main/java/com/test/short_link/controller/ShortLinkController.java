package com.test.short_link.controller;

import com.test.short_link.common.result.ResultBody;
import com.test.short_link.domain.dto.AliasCustomDto;
import com.test.short_link.domain.dto.ShortLinkDto;
import com.test.short_link.domain.dto.ShortLinkListDto;
import com.test.short_link.service.ShortLinkService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ShortLinkController {
    private final ShortLinkService shortLinkService;

    /**
     * Short Link 변환 API
     *
     * @param shortLinkDto
     * @return
     * @throws Exception
     */
    @PostMapping("/short-links")
    public ResultBody createShortLink (@Valid ShortLinkDto shortLinkDto) throws Exception {
        try {
            // url 변환 & Short Link 세팅
            shortLinkDto.setShortId(shortLinkService.convertUrlToShort());
            // data insert
            shortLinkService.insertShortLink(shortLinkDto);
            // data return
            return new ResultBody(shortLinkService.selectShortLinkById(shortLinkDto.getShortId()));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Short Link - URL 접속 API
     *
     * @param short_id
     * @param response
     * @throws Exception
     */
    @GetMapping(value = "/r/{short_id}")
    public void redirectShortLink (@PathVariable String short_id, HttpServletResponse response) throws Exception {
        try {
            response.sendRedirect(shortLinkService.selectUrlByShortId(short_id));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Short Links 목록 조회 API
     *
     * @param shortLinkListDto
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/short-links")
    public ResultBody selectShortLinks (@Valid ShortLinkListDto shortLinkListDto) throws Exception {
        try {
            // Short Link 목록 조회 후 ResultBody 에 넣어서 return
            return new ResultBody(shortLinkService.selectShortLinkList(shortLinkListDto));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 특정 Short Links 조회 API
     *
     * @param short_id
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/short-links/{short_id}")
    public ResultBody selectShortLink (@PathVariable String short_id) throws Exception {
        try {
            return new ResultBody(shortLinkService.selectShortLinkById(short_id));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Short_id - Alias_name 변경 API
     *
     * @param short_id
     * @param aliasCustomDto
     * @return
     * @throws Exception
     */
    @PatchMapping(value = "/short-links/{short_id}")
    public ResultBody customizeAlias (
            @PathVariable String short_id,
            @Valid AliasCustomDto aliasCustomDto
    ) throws Exception {
        try {
            // 중복 확인
            if (shortLinkService.isExistShortLinkById(aliasCustomDto.getAliasName()))
                throw new Exception("중복된 alias 입니다. 다시 입력해주세요.");

            // AliasCustomDto 세팅 & 변환
            aliasCustomDto.setShortId(short_id);
            shortLinkService.customizeShortId(aliasCustomDto);

            return new ResultBody("update complete");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Alias_name - URL 접속 API
     *
     * @param alias_name
     * @param response
     * @throws Exception
     */
    @GetMapping(value = "/a/{alias_name}")
    public void redirectAliasCustom (@PathVariable String alias_name, HttpServletResponse response) throws Exception {
        try {
            response.sendRedirect(shortLinkService.selectUrlByShortId(alias_name));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 특정 Short Link 삭제 API
     *
     * @param short_id
     * @return
     * @throws Exception
     */
    @DeleteMapping(value = "/short-links/{short_id}")
    public ResultBody deleteShortLink (@PathVariable String short_id) throws Exception {
        try {
            // 아이디 값 체크
            if (!shortLinkService.isExistShortLinkById(short_id))
                throw new Exception("존재하지 않는 Short ID 입니다.");
            // 삭제
            shortLinkService.deleteShortLinkById(short_id);

            return new ResultBody("delete complete");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
