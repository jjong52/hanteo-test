package com.hanteoglobal;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * 카테고리를 표현하는 클래스.
 * 각 카테고리는 고유한 ID, 이름, 그리고 하위 카테고리 리스트를 가집니다.
 */
@Getter
public class Category {
    private Integer id;
    private String name;
    private List<Category> children = new ArrayList<>();

    public Category(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addChild(Category category) {
        this.children.add(category);
    }

}
