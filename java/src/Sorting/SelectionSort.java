package Sorting;

import java.util.Arrays;

public class SelectionSort {

    public static void sortProblem(int[] input){

        int n = input.length;

        // Time complexity: O(n ^2)

        for(int i=0;i<n-1;i++){

            int temp = i;

            for(int j=i+1;j<n;j++){

                if(input[j] < input[temp]){
                    temp = j;
                }
            }

            int swap = input[i];
            input[i] = input[temp];
            input[temp] = swap;
        }
    }

    public static void main(String[] args){

        int[][] inputs = new int[][]{{5,1,4,8,2} ,{ 0 , 0, -1, 5,9, 2,1}};

        for(int i=0;i<inputs.length;i++){

            System.out.println("Sorting "+ (i +1) +" input");
            sortProblem(inputs[i]);
            System.out.println(Arrays.toString(inputs[i]));
        }

    }
}
