package main.java.Sorting;

import java.util.Arrays;

public class InsertionSort {

    public static void sortProblem(int[] input){

        int n = input.length;

        // [ 5 1 4 8 2]

        for(int i = 1;i < n;i++){

            int temp = input[i]; // 4 , i  =2

            int j = i - 1; //  j = 1

            while(j>=0 && input[j] > temp){
                input[j+1] = input[j]; //[ 1 4 4 8 2]
                j--;
            }

            input[j+1] = temp;// [ 1 4 5 8 2]
        }
    }

    public static void main(String[] args){

        int[][] inputs = new int[][]{{5,1,4,8,2} ,{ 0 , 0, -1, 5,9, 2,1}};

        for(int i=0;i<inputs.length;i++){

            System.out.println("main/java/Sorting " + (i +1) +" input");
            sortProblem(inputs[i]);
            System.out.println(Arrays.toString(inputs[i]));
        }

    }
}
