/*

You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night. 
Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police. 
*/

package com.company;

import java.util.Scanner;

public class Assinment2 {
    private static int jump(int[] arr, int size, int index) {
        if (index >= size) return 0;
        int j1 = jump(arr, size, index + 2) + arr[index];
        int j2 = jump(arr, size, index + 3) + arr[index];
        return j1 < j2 ? j2 : j1;
    }

    private static void robberRob(int[] arrayInt) {
        int size = arrayInt.length;
        int result1 = jump(arrayInt, size, 0);
        int result2 = jump(arrayInt, size, 1);
        if (result1 < result2) {
            System.out.println("\n" + result2);
        } else {
            System.out.println("\n" + result1);
        }
    }

    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        System.out.print("Enter Array Size : ");
        int size = myObj.nextInt();
        int[] arrayInt = new int[size];
        System.out.print("Enter Numbers one by one :\n");
        for (int i = 0; i < size; i++)
            arrayInt[i] = myObj.nextInt();
        robberRob(arrayInt);
    }
}
