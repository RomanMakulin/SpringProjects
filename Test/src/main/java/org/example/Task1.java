package org.example;

import java.util.Random;

public class Task1 {

    /*
В массиве содержатся все числа от 1 до 100, причем одно из них встречается дважды.
Напишите метод, принимающий указанный массив, который вернет дублирующийся элемент.
*/

    public static int[] initArray() {
        int[] arr = new int[101];
        int minNumber = 1;
        int maxNumber = 100;
        for (int nubmer = minNumber, pos = 0; nubmer <= maxNumber; nubmer++, pos++) {
            arr[pos] = nubmer;
        }
        Random random = new Random();
        int randomNumber = random.nextInt(maxNumber - minNumber + 1) + minNumber;
        System.out.println("randomNumber is " + randomNumber);
        arr[100] = randomNumber;
        return arr;
    }



}
