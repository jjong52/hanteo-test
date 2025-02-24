package com.hanteoglobal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

/**
 * 카테고리를 표현하는 클래스.
 * 각 카테고리는 고유한 ID, 이름, 그리고 하위 카테고리 리스트를 가집니다.
 */
@Getter
public class Category {
    private Integer id;
    private String name;

    public Category(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * 카테고리를 JSON 형식으로 반환합니다.
     *
     * @return 카테고리의 JSON 문자열
     * @throws JsonProcessingException JSON 변환 중 오류 발생 시 예외 처리
     */

    public String toJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
    }

}
