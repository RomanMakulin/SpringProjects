package org.example;

import java.util.HashSet;
import java.util.Set;

public class Task2 {

//Имеется строка s на входе. Разделение этой строки на 2 непустые подстроки p и q
//называется хорошим тогда и только тогда, когда конкатенация этих строк дает строку s
//и количество различных элементов в подстроке p равно количеству различных в q. Напишите метод,
//принимающий строку s, который подсчитывает количество "хороших" разделений строки s.

    /**
     * Актуально. Второй способ реализации (наиболее комфортный)
     *
     * @param s передаваемая строка
     * @return количество успешных исходов
     */
    public int countOfGoodSecond(String s) {
        StringBuilder firstSubString = new StringBuilder();
        int count = 0;
        for (int i = 0; i + 1 < s.length(); i++) {
            firstSubString.append(s.charAt(i));
            if (firstSubString.chars().distinct().count() == s.substring(i + 1).chars().distinct().count()) count++;
        }
        return count;
    }

    /**
     * Неакутально. Первый способ реализации (оставил его для понимания что сначала хотел сделать ...
     * ... и из чего доработал до хорошей версии)
     *
     * @param s передаваемая строка
     * @return количество успешных исходов
     */
    public int countOfGoodFirst(String s) {
        StringBuilder firstSubString = new StringBuilder();
        String secondSubString = "";
        int count = 0;

        for (int i = 0; i + 1 < s.length(); i++) {
            firstSubString.append(s.charAt(i));
            secondSubString = s.substring(i + 1);

            Set<Character> uniqueCharsFromFirstString = new HashSet<>();
            Set<Character> uniqueCharsFromSecondString = new HashSet<>();

            for (char ch : firstSubString.toString().toCharArray()) uniqueCharsFromFirstString.add(ch);
            for (char ch : secondSubString.toCharArray()) uniqueCharsFromSecondString.add(ch);
            if (uniqueCharsFromFirstString.size() == uniqueCharsFromSecondString.size()) count++;
        }
        return count;
    }
}
