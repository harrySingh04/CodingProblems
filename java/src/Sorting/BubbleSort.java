package Sorting;

import java.util.Arrays;

public class BubbleSort {

    public static void sortProblem(int[] array){

        int n = array.length;

        // Time complexity: O(n ^2 )

        for(int i=0;i<n-1;i++){

            boolean swap = false;

            for(int j = 0 ; j < n - i-1;j++){

                    if(array[j] > array[j+1]){

                        int temp = array[j];
                        array[j] = array[j+1];
                        array[j+1] = temp;
                        swap = true;
                    }

            }

            if(!swap)
                break; // when we have all sorted

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
