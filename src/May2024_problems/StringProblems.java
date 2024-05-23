package May2024_problems;

import java.util.HashMap;
import java.util.Map;

public class StringProblems {

    public int countAnagramsOccurance(String string1, String string2){

        Map<Character,Integer> characterCount = new HashMap<>();


        for(int i=0;i<string2.length();i++){
            char ch = string2.charAt(i);

            if(characterCount.containsKey(ch))
                characterCount.put(ch,characterCount.get(ch)+1);
            else
                characterCount.put(ch,1);

        }

        int i,j, k, result = 0, countUnique;

        j = 0;
        i = 0;
        k = string2.length();

        countUnique = characterCount.size();

        while(j < string1.length()){

            char ch = string1.charAt(j);

            if(characterCount.containsKey(ch)) {
                characterCount.put(ch, characterCount.get(ch) - 1);
                if(characterCount.get(ch) == 0)
                    countUnique--;
            }

            if(j - i + 1 < k)
                j++;
            else if(j - i + 1 == k){

                if(countUnique == 0) {
                    result++;
                }

                if(characterCount.containsKey(string1.charAt(i)))
                {
                    char ch2 = string1.charAt(i);

                    if(characterCount.get(ch2) == 0){
                        countUnique++;
                    }
                    characterCount.put(ch2,characterCount.get(ch2)+1);
                }

                i++;
                j++;

            }
        }

        return result;
    }
}
