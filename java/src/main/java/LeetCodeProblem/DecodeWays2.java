package main.java.LeetCodeProblem;

public class DecodeWays2 {

    private final int MOD = 1000000007;


    public int solve(int start, String s, int n) { // 0 s n


        if (start >= n)
            return 1;

        if (s.charAt(start) == '0')
            return 0;

        int multiplier = 1;
        if (s.charAt(start) == '*')
            multiplier = 9;

        int result = (multiplier * (solve(start + 1, s, n) % MOD) % MOD);

        // result = 9

        // * 1 ...
        // * 2 ....

        // * 3....

        // * 9...

        if (start + 1 < n) {

            if (s.charAt(start) == '*' && s.charAt(start + 1) == '*')
                result += 15 * (solve(start + 2, s, n) % MOD);
            else if (s.charAt(start) == '*' && s.charAt(start + 1) <= '6')
                result += 2 * (solve(start + 2, s, n) % MOD);
            else if (s.charAt(start) == '*' && s.charAt(start + 1) > '6')
                result += 1 * (solve(start + 2, s, n) % MOD);
            else if (s.charAt(start) == '1' && s.charAt(start + 1) == '*')
                result += 9 * (solve(start + 2, s, n) % MOD);
            else if (s.charAt(start) == '2' && s.charAt(start + 1) == '*')
                result += 6 * (solve(start + 2, s, n) % MOD);
            else if (s.charAt(start) == '1' || (s.charAt(start) == '2'
                    && s.charAt(start + 1) <= '6'))
                result += (solve(start + 2, s, n) % MOD);


        }

        return result % MOD;

    }

    public int numDecodings(String s) {

        int n = s.length();

        return solve(0, s, n);


    }

    public static void main(String[] args){
        DecodeWays2 d1 = new DecodeWays2();
        System.out.println(d1.numDecodings("7*9*3*6*3*0*5*4*9*7*3*7*1*8*3*2*0*0*6*"));
    }
}
