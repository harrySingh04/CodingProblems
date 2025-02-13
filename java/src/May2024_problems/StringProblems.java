package May2024_problems;

import java.util.*;

public class StringProblems {

    public int countAnagramsOccurance(String string1, String string2) {

        Map<Character, Integer> characterCount = new HashMap<>();


        for (int i = 0; i < string2.length(); i++) {
            char ch = string2.charAt(i);

            if (characterCount.containsKey(ch))
                characterCount.put(ch, characterCount.get(ch) + 1);
            else
                characterCount.put(ch, 1);

        }

        int i, j, k, result = 0, countUnique;

        j = 0;
        i = 0;
        k = string2.length();

        countUnique = characterCount.size();

        while (j < string1.length()) {

            char ch = string1.charAt(j);

            if (characterCount.containsKey(ch)) {
                characterCount.put(ch, characterCount.get(ch) - 1);
                if (characterCount.get(ch) == 0)
                    countUnique--;
            }

            if (j - i + 1 < k)
                j++;
            else if (j - i + 1 == k) {

                if (countUnique == 0) {
                    result++;
                }

                if (characterCount.containsKey(string1.charAt(i))) {
                    char ch2 = string1.charAt(i);

                    if (characterCount.get(ch2) == 0) {
                        countUnique++;
                    }
                    characterCount.put(ch2, characterCount.get(ch2) + 1);
                }

                i++;
                j++;

            }
        }

        return result;
    }


    /**
     * Given a string, s, that represents a DNA subsequence, and a number k, return all the contiguous subsequences
     * (substrings) of length k that occur more than once in the string.
     * The order of the returned subsequences does not matter.
     * If no repeated substring is found, the function should return an empty set.
     * The DNA sequence is composed of a series of nucleotides abbreviated as A,C,G,and T. For example,
     * ACGAATTCCG is a DNA sequence. When studying DNA, it is useful to identify repeated sequences in it.
     * Constraints:
     * 1 ≤ s.length ≤ 10 ^ 3
     * s[i] is either A, C, G, or T.
     * 1≤ k ≤ 10
     */


    public static Set<String> findRepeatedSequences(String s, int k) {

        int n = s.length();

        if (n < k)
            return new HashSet<>();

        Map<Character, Integer> mapping = new HashMap<>();
        mapping.put('A', 1);
        mapping.put('C', 2);
        mapping.put('G', 3);
        mapping.put('T', 4);

        int a = 4;

        List<Integer> mappingNumbers = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            mappingNumbers.add(mapping.get(s.charAt(i)));
        }

        Set<Integer> hashSet = new HashSet<>();
        Set<String> output = new HashSet<>();

        int hashValue = 0;

        for (int i = 0; i < n - k + 1; i++) {

            if (i == 0) {
                for (int j = 0; j < k; j++) {
                    hashValue += mappingNumbers.get(j) * (int) Math.pow(a, k - j - 1);

                }
            } else {
                int previousHashValue = hashValue;
                hashValue = ((previousHashValue - mappingNumbers.get(i - 1) * (int) Math.pow(a, k - 1)) * a) + mappingNumbers.get(i + k - 1);
            }

            if (hashSet.contains(hashValue)) {
                output.add(s.substring(i, i + k));
            }

            hashSet.add(hashValue);
        }

        return output;

    }

    public static void main(String[] args) {

        StringProblems s1 = new StringProblems();

        //System.out.println(s1.wordBreak("leetcode",new ArrayList<>(List.of("leet","code"))));
        System.out.println(s1.nearestPalindromic("123"));
        System.out.println(s1.nearestPalindromic("129"));
        System.out.println(s1.nearestPalindromic("1234"));
        System.out.println(s1.nearestPalindromic("888"));
        /*List<String> inputsString = Arrays.asList("ACGT", "AGACCTAGAC", "AAAAACCCCCAAAAACCCCCC",
                "GGGGGGGGGGGGGGGGGGGGGGGGG", "TTTTTCCCCCCCTTTTTTCCCCCCCTTTTTTT", "TTTTTGGGTTTTCCA",
                "AAAAAACCCCCCCAAAAAAAACCCCCCCTG", "ATATATATATATATAT");
        List<Integer> inputsK = Arrays.asList(3, 3, 8, 12, 10, 14, 10, 6);

        for (int i = 0; i < inputsK.size(); i++) {
            System.out.println((i + 1) + ".\tInput sequence: " + inputsString.get(i) +
                    "\n\tk: " + inputsK.get(i) +
                    "\n\n\tRepeated sequences: " + findRepeatedSequences(inputsString.get(i), inputsK.get(i)));
        }*/

        // Driver code
       /* String[] str1 = {
                "abcdedeaqdweq", "fgrqsqsnodwmxzkzxwqegkndaa", "zxcvnhss", "alpha", "beta"
        };
        String[] str2 = {
                "adeq", "kzed", "css", "la", "ab"
        };
        for (int i = 0; i < str1.length; i++) {
            System.out.println(i + 1 + ".\tInput String: " + "(" + str1[i] + ", " + str2[i] + ")");
            System.out.println("\tSubsequence string: " + minWindow(str1[i], str2[i]));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }*/

      /*  List<String> inputStrings = Arrays.asList("aabccbb", "abbcb", "abccde", "abbcab", "bbbbbbbbb");
        List<Integer> k = Arrays.asList(2, 1, 1, 2, 4);

        for (int i = 0; i < inputStrings.size(); ++i) {
            System.out.println((i + 1) + ".\tInput String: '" + inputStrings.get(i) + "'");
            System.out.println("\tk: " + k.get(i));
            System.out.println("\tLength of the longest substring with repeating characters: "
                    + longestRepeatingCharacterReplacement(inputStrings.get(i), k.get(i)));
            System.out.println(new String(new char[100]).replace("\0", "-"));
        }
       */


    }


    /**
     *
     */

    // Worst case time complexity is O( n * m)
    public static String minWindow(String s, String t) {

        // Replace this placeholder return statement with your code

        int i = 0, j = 0, k = 0, m = s.length(), n = t.length(),
                start = 0, end = 0, minLength = Integer.MAX_VALUE;


        while (j < m && k < n) {

            if (s.charAt(j) == t.charAt(k))
                k++;

            if (k == n) {
                i = j;
                k = n - 1;

                while (i >= 0 && k >= 0) {
                    if (s.charAt(i) == t.charAt(k))
                        k--;
                    i--;
                }

                i++;

                if (minLength > j - i + 1) {
                    start = i;
                    end = j + 1;
                    minLength = j - i + 1;
                }
                k = 0;
                j = i + 1;

            } else
                j++;

        }
        return s.substring(start, end);
    }

    public static int longestRepeatingCharacterReplacement(String s, int k) {


        int i = 0, j = 0, maxLength = Integer.MIN_VALUE;

        Map<Character, Integer> characterFreq = new HashMap<>();

        int maxCharFreq = 0;

        char currentChar;

        while (j < s.length()) {

            currentChar = s.charAt(j);

            characterFreq.put(currentChar, characterFreq.getOrDefault(currentChar, 0) + 1);

            maxCharFreq = Math.max(maxCharFreq, characterFreq.get(currentChar));

            // More than window size
            if (j - i + 1 - maxCharFreq > k) {
                // remove from i
                characterFreq.put(s.charAt(i), characterFreq.get(s.charAt(i)) - 1);
                i++;
            }

            maxLength = Math.max(maxLength, j - i + 1);
            j++;

        }
        return maxLength;
    }

    public static String minWindowSubstring(String s, String t) {

        // Replace this placeholder return statement with your code

        int i = 0, j = 0, minLength = Integer.MAX_VALUE, count, start = 0, end = -1;

        Map<Character, Integer> freqMap = new HashMap<>();

        for (int a = 0; a < t.length(); a++) {

            freqMap.put(t.charAt(a), freqMap.getOrDefault(t.charAt(a), 0) + 1);
        }

        count = freqMap.size();


        while (j < s.length()) {

            char ch = s.charAt(j);

            if (freqMap.containsKey(ch)) {

                freqMap.put(ch, freqMap.get(ch) - 1);

                if (freqMap.get(ch) == 0)
                    count--;

            }


            while (count == 0) {
                if (j - i + 1 < minLength) {
                    minLength = j - i + 1;
                    start = i;
                    end = j;
                }
                if (freqMap.containsKey(s.charAt(i))) {
                    freqMap.put(s.charAt(i), freqMap.get(s.charAt(i)) + 1);
                    if (freqMap.get(s.charAt(i)) > 0)
                        count++;
                }
                i++;

            }

            j++;
        }

        return s.substring(start, end + 1);
    }


    public boolean wordBreak(String s, List<String> wordDict) {

        // leetcode , ["leet", "code"]

        //dp[0] = T

        boolean[] dp = new boolean[s.length() + 1];

        dp[0] = true;

        Set<String> wordSet = new HashSet<>(wordDict);

        for(int i =1;i < dp.length;i++){

            for(int j = 1 ; j <= i;j++){

                dp[i] = dp[i] || dp[i-j] && wordSet.contains(s.substring(i - j, i));
            }
        }

        return dp[s.length()];



    }

    public static int findLongestSubstring(String str) {

        // Replace this placeholder return statement with your code

        int i = 0, j = 0, maxLength = Integer.MIN_VALUE;

        Map<Character, Integer> charFreq = new HashMap<>();

        while (j < str.length()) {

            charFreq.put(str.charAt(j), charFreq.getOrDefault(str.charAt(j), 0) + 1);

            if (charFreq.size() == j - i + 1)
                maxLength = Math.max(maxLength, j - i + 1);

            while (charFreq.size() < j - i + 1) {
                if (charFreq.containsKey(str.charAt(i))) {
                    charFreq.put(str.charAt(i), charFreq.get(str.charAt(i)) - 1);
                    if (charFreq.get(str.charAt(i)) == 0)
                        charFreq.remove(str.charAt(i));

                }
                i++;
            }

            j++;

        }

        return maxLength;
    }

        // Convert to palindrome keeping first half constant.
        private long convert(long num) {
            String s = Long.toString(num);
            int n = s.length();
            int l = (n - 1) / 2, r = n / 2;
            char[] sArray = s.toCharArray();
            while (l >= 0) {
                sArray[r++] = sArray[l--];
            }
            return Long.parseLong(new String(sArray));
        }

        // Find the previous palindrome, just smaller than n.
        private long previousPalindrome(long num) {
            long left = 0, right = num;
            long ans = Long.MIN_VALUE;
            while (left <= right) {
                long mid = (right - left) / 2 + left;
                long palin = convert(mid);
                if (palin < num) {
                    ans = palin;
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return ans;
        }

        // Find the next palindrome, just greater than n.
        private long nextPalindrome(long num) {
            long left = num, right = (long) 1e18;
            long ans = Long.MIN_VALUE;
            while (left <= right) {
                long mid = (right - left) / 2 + left;
                long palin = convert(mid);
                if (palin > num) {
                    ans = palin;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            return ans;
        }

        public String nearestPalindromic(String n) {
            long num = Long.parseLong(n);
            long a = previousPalindrome(num);
            long b = nextPalindrome(num);
            if (Math.abs(a - num) <= Math.abs(b - num)) {
                return Long.toString(a);
            }
            return Long.toString(b);
        }
}
