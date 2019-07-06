/*
1.Take an Array of N Integers 
2. Accept an input m 
3. The algorithm should pick elements from the array and sum them so that it gives the value m 
4. if the above is not possible then it should return -1 otherwise it should return 1 and display the elements used to make the sum. 
*/
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Assignment1 {
    private static int status;

    private static int calculate(int[] arr, int size, int total) {
        status = -1;
        for (int i = 0; i <= size; i++) {
            calculateBySelect(arr, size, total, i);
        }
        return status;
    }

    private static void calculateBySelect(int[] arr, int size, int total, int select) {
        combinationOfEach(arr, size, select, total);
    }

    private static void combination(int[] arr, int[] data, int start, int end, int index, int r, int total) {
        if (index == r) {
            int sum = 0;
            String s = "Add(";
            for (int j = 0; j < r; j++) {
                sum = sum + data[j];
                s = s + " " + data[j];
            }
            if (sum == total) {
                status = 1;
                System.out.println(s + " ) =  " + sum);
            }
            return;
        }
        for (int i = start; i <= end && end - i + 1 >= r - index; i++) {
            data[index] = arr[i];
            combination(arr, data, i + 1, end, index + 1, r, total);
        }
    }

    private static void combinationOfEach(int[] arr, int n, int r, int total) {
        int[] data = new int[r];
        combination(arr, data, 0, n - 1, 0, r, total);
    }

    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        System.out.print("Enter Array Size : ");
        int size = myObj.nextInt();
        List<Integer> arr = new ArrayList<>(size);
        System.out.print("Enter Numbers one by one :\n");
        for (int i = 0; i < size; i++)
            arr.add(myObj.nextInt());
        System.out.print("Enter Total value should be : ");
        int total = myObj.nextInt();
        Collections.sort(arr);
        Integer[] array = arr.stream().toArray(n -> new Integer[n]);
        int[] arrayInt = new int[size];
        for (int i = 0; i < size; i++)
            arrayInt[i] = array[i];
        System.out.print("Number returned is :" + calculate(arrayInt, size, total));
    }
}
