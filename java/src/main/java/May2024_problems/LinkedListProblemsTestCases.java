package main.java.May2024_problems;

import org.junit.Assert;
import org.junit.Test;

public class LinkedListProblemsTestCases {

    @Test
    public void testPalindromeLinkedList(){
        LinkedListProblems problems = new LinkedListProblems();

        int[][] input={
                {2, 4, 6, 4, 2},
                {0, 3, 5, 5, 0},
                {9, 27, 4, 4, 27, 9},
                {5, 4, 7, 9, 4, 5},
                {5, 10, 15, 20, 15, 10, 5}
        };

        boolean[] expectedOutput = {true,false,true,false,true};

        for(int i=0; i<input.length; i++){
            LinkedList list = new LinkedList();
            list.createLinkedList(input[i]);
            Assert.assertEquals(expectedOutput[i],problems.checkPalindrome(list));
        }

    }
}
