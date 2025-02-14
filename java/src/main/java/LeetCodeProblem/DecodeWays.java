package main.java.LeetCodeProblem;

class DecodeWays {

    public int solve(int start, String s, int n) {
        if (start >= n)
            return 1;

        if (s.charAt(start) == '0')
            return 0;

        int result = solve(start + 1, s, n);

        if (start + 1 < n) {
            if (s.charAt(start) == '1' || (s.charAt(start) == '2' && s.charAt(start + 1) <= '6'))
                result += solve(start + 2, s, n);
        }

        return result;
    }

    public int numDecodings(String s) {
        int n = s.length();
        return solve(0, s, n);

    }


    public int numDecodingsBottomUp(String s) {
        int n = s.length();
        int[] dp = new int[n+1];
        //Arrays.fill(dp, -1);
        //return solve(0, s, n, dp);

        dp[n] = 1;

        for(int i = n -1;i>=0;i--){

            if(s.charAt(i) == '0')
                dp[i] = 0;
            else
            {
                dp[i] = dp[i + 1];
                if(i < n-1 && (s.charAt(i) == '1' || (s.charAt(i)  == '2' && s.charAt(i+1) <= '6')))
                    dp[i] += dp[i+2];
            }

        }

        return dp[0];

        /**
         11106 -> 1 1106 , 11 106
         */

    }




    public static void main(String[] args){
        DecodeWays d1 = new DecodeWays();
        System.out.println(d1.numDecodingsBottomUp("11106"));
    }
}
