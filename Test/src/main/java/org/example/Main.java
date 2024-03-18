package org.example;


import java.util.HashSet;
import java.util.Set;


//Имеется строка s на входе. Разделение этой строки на 2 непустые подстроки p и q
//называется хорошим тогда и только тогда, когда конкатенация этих строк дает строку s
//и количество различных элементов в подстроке p равно количеству различных в q. Напишите метод,
//принимающий строку s, который подсчитывает количество "хороших" разделений строки s.

public class Main {
    public static void main(String[] args) {
        // bad try: реализация первой идеи в рамках собеседования
        System.out.println(countOfGoodFirst("aacaba") + " count of good line split #1");

        // nice try: подумал и сделал как положено
        System.out.println(countOfGoodSecond("aacaba") + " count of good line split #2");
    }

    public static int countOfGoodSecond(String s) {
        StringBuilder firstSubString = new StringBuilder();
        int count = 0;
        for (int i = 0; i + 1 < s.length(); i++) {
            firstSubString.append(s.charAt(i));
            if (firstSubString.chars().distinct().count() == s.substring(i + 1).chars().distinct().count()) count++;
        }
        return count;
    }

    public static int countOfGoodFirst(String s) {
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