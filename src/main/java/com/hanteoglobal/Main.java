package com.hanteoglobal;

import com.fasterxml.jackson.core.JsonProcessingException;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {

        // 1번 문제
        CategoryTree tree = new CategoryTree();

        tree.addCategory(1, "남자", null);
        tree.addCategory(2, "여자", null);


        tree.addCategory(3, "엑소", 1);

        tree.addCategory(6, "공지사항", 3);
        tree.addCategory(7, "첸", 3);
        tree.addCategory(8, "백현", 3);
        tree.addCategory(9, "시우민", 3);



        tree.addCategory(4, "방탄 소년단", 1);

        tree.addCategory(10, "공지사항", 4);
        tree.addCategory(11, "익명게시판", 4);
        tree.addCategory(12, "뷔", 4);



        tree.addCategory(5, "블랙핑크", 2);

        tree.addCategory(13, "공지사항", 5);
        tree.addCategory(14, "익명게시판", 5);
        tree.addCategory(15, "로제", 5);



        tree.printCategoryTreeAsJson();





        // 2번 문제
        int[] coins1 = {1, 2, 3};
        int sum1 = 4;
        System.out.println("출력: " + CoinGame.coinGame(sum1, coins1)); // 4

        int[] coins2 = {2, 5, 3, 6};
        int sum2 = 10;
        System.out.println("출력: " + CoinGame.coinGame(sum2, coins2)); // 5
    }
}