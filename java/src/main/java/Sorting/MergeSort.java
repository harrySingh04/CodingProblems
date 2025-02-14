package main.java.Sorting;

import java.util.Arrays;

public class MergeSort {


    public static void mergeSort(int[] input, int low , int high){

        if(low >= high)
            return;

        int mid = low + (high - low)/2;

        mergeSort(input, low, mid);
        mergeSort(input, mid + 1, high);
        mergeSortedList(input, low, mid, high);
    }

    private static void mergeSortedList(int[] arr, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                i++;
            } else {
                int temp = arr[j];
                for (int k = j; k > i; k--) {
                    arr[k] = arr[k - 1];
                }
                arr[i] = temp;
                i++;
                mid++;
                j++;
            }
        }
    }

    public static void sortProblem(int[] input){
        int n = input.length;

        int low = 0, high = n - 1;

        mergeSort(input, low , high);
    }


    public static void main(String[] args){

        int[][] inputs = new int[][]{{5,1,4,8,2} ,{ 0 , 0, -1, 5,9, 2,1},{110, 100, 0}};

        for(int i=0;i<inputs.length;i++){

            System.out.println("main/java/Sorting " + (i +1) +" input");
            sortProblem(inputs[i]);
            System.out.println(Arrays.toString(inputs[i]));
        }

    }
}
