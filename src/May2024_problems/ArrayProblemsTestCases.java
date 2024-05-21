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

}
