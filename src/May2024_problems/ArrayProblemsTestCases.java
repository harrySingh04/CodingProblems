package May2024_problems;

import org.junit.Assert;
import org.junit.Test;


public class ArrayProblemsTestCases {


    @Test
    public void testReverseBitsCaseOne(){
        ArrayProblems arrayProblems = new ArrayProblems();
        Assert.assertEquals(1260388352,arrayProblems.reverseBits(1234));
        Assert.assertEquals(3221225471L,arrayProblems.reverseBits(4294967293L));
        Assert.assertEquals(2684354560L,arrayProblems.reverseBits(5));
        Assert.assertEquals(1044709376,arrayProblems.reverseBits(41596));
        Assert.assertEquals(2355527680L,arrayProblems.reverseBits(91697));

    }

    @Test
    public void testArrayCyclic(){
        ArrayProblems arrayProblems = new ArrayProblems();
        int[][] input = {
                {-2, -3, -9},
                {-5, -4, -3, -2, -1},
                {-1, -2, -3, -4, -5},
                {2, 1, -1, -2},
                {-1, -2, -3, -4, -5, 6},
                {1, 2, -3, 3, 4, 7, 1},
                {2, 2, 2, 7, 2, -1, 2, -1, -1}
        };

        boolean[] expectedResults = {false,true,false,false,false,true,true};

        for (int i = 0; i < input.length; i++) {
            System.out.println("Test case number = "+(i+1) );

            boolean result = arrayProblems.circularArrayLoop(input[i]);
            Assert.assertEquals(expectedResults[i],result);
        }
    }

}
