package May2024_problems;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StringProblemsTestCases {

    StringProblems stringProblems;

    @Before
    public void setUp(){
        this.stringProblems = new StringProblems();
    }

    @Test
    public void testAnagramOccurance(){

        String[] inputStringOne = {
                "forxxorfxdofr",
                "aabaabaa"
        };

        String[] anagramStringCheck = {
                "for",
                "aaba"
        };

        int[] expectedOutput = {3,4};

        for(int i=0;i<inputStringOne.length;i++){

            Assert.assertEquals(expectedOutput[i], stringProblems.countAnagramsOccurance(inputStringOne[i], anagramStringCheck[i]));
        }


    }
}
