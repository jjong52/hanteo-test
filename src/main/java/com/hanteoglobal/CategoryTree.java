package com.hanteoglobal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

/**
 * 카테고리 트리를 관리하는 클래스.
 * 카테고리 간의 관계를 관리하고, 검색 및 JSON 변환 기능을 제공합니다.
 */
public class CategoryTree {
    private Map<Integer,Category> categoryMap;
    private Map<Integer, List<Integer>> parentChildMap; // 관계 데이터 저장 (parent_id -> child_id)

    CategoryTree() {
        this.categoryMap = new HashMap<>();
        this.parentChildMap = new HashMap<>();
    }

    /**
     * 카테고리를 추가합니다.
     *
     * @param categoryId 추가할 카테고리 ID
     * @param name       카테고리 이름
     * @param parentId   부모 카테고리 ID (없는 경우 null)
     */
    void addCategory(Integer categoryId, String name, Integer parentId) {
        // 이미 존재하는 카테고리 ID인지 확인
        if (categoryMap.containsKey(categoryId)) {
            throw new IllegalArgumentException("이미 존재하는 카테고리 ID=" + categoryId + "입니다.");
        }

        // 카테고리 생성
        Category category = new Category(categoryId, name);
        this.categoryMap.put(categoryId,category);

        // 부모 카테고리 아이디 존재 여부 확인
        if (parentId != null && !categoryMap.containsKey(parentId)) {
            throw new IllegalArgumentException("존재하지 않는 부모 카테고리 ID=" + parentId + "입니다.");
        }

        if (parentId != null) {
            // 부모 카테고리의 ID가 parentChildMap 에 존재하지 않으면 빈 리스트 생성후 자식 카테고리 ID 추가
            parentChildMap.computeIfAbsent(parentId, k -> new ArrayList<>()).add(categoryId);
        }

    }

    /**
     * 카테고리 ID로 카테고리를 검색합니다.
     *
     * @param id 검색할 카테고리 ID
     * @return 검색된 카테고리 (없는 경우 null)
     */
    Category findCategoryById(int id) {
        return this.categoryMap.get(id);
    }


    /**
     * 카테고리 이름으로 카테고리들을 검색합니다.
     *
     * @param name 검색할 카테고리 이름
     * @return 이름이 일치하는 카테고리들의 리스트 (없는 경우 빈 리스트 반환)
     */
    List<Category> findCategoriesByName(String name) {
        List<Category> matchingCategories = new ArrayList<>();
        for (Category category : categoryMap.values()) {
            if (category.getName().equalsIgnoreCase(name)) {
                matchingCategories.add(category);
            }
        }
        return matchingCategories;


    }

    /**
     * 카테고리 이름으로 카테고리들을 부분 검색합니다.
     *
     * @param name 검색할 카테고리 이름 (부분 문자열)
     * @return 이름에 부분 문자열이 포함된 카테고리들의 리스트 (없는 경우 빈 리스트 반환)
     */
    List<Category> findCategoriesByNameContains(String name) {
        List<Category> matchingCategories = new ArrayList<>();
        String searchName = name.toLowerCase(); // 대소문자 구분 없이 검색

        for (Category category : categoryMap.values()) {
            if (category.getName().toLowerCase().contains(searchName)) {
                matchingCategories.add(category);
            }
        }
        return matchingCategories;
    }



    /**
     * 카테고리 트리를 JSON 형식으로 반환합니다.
     *
     * @return 카테고리 트리의 JSON 문자열
     * @throws JsonProcessingException JSON 변환 시 오류 발생 시
     */
    String toJson() throws JsonProcessingException {
        // 카테고리와 부모-자식 관계를 포함하는 트리 형태의 맵을 작성하여 JSON으로 변환
        Map<String, Object> categoryTreeMap = new HashMap<>();
        categoryTreeMap.put("categories", categoryMap);
        categoryTreeMap.put("relations", parentChildMap);

        // ObjectMapper를 사용하여 JSON 문자열로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(categoryTreeMap);
    }


}
