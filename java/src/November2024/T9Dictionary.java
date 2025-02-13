package November2024;

import java.util.*;

public class T9Dictionary {

    Trie root = new Trie();

    public void addWord(String word){

        Trie current = root;

        for(char c: word.toCharArray()){

            if(current.children[c - 'a'] == null)
                current.children[c-'a'] = new Trie();
            current = current.children[c-'a'];
        }

        current.isWord = true;
    }




    Map<Character,String> lettersMap;

    public T9Dictionary(){
        lettersMap = new HashMap<>();
        lettersMap.put('2',"abc");
        lettersMap.put('3',"def");
        lettersMap.put('4',"ghi");
        lettersMap.put('5',"jkl");
        lettersMap.put('6',"mno");
        lettersMap.put('7',"pqrs");
        lettersMap.put('8',"tuv");
        lettersMap.put('9',"wxyz");

    }

    public void solve(String number , int idx, Trie node, StringBuilder sb, List<String> results) {

        if (node.isWord) {
                results.add(sb.toString());
            }

        if(idx == number.length())
            return;

        String mappedLetter = lettersMap.get(number.charAt(idx));

        if (mappedLetter != null) {
            for (char c : mappedLetter.toCharArray()) {
                Trie child = node.children[c - 'a'];
                if (child != null) {
                    sb.append(c);
                    solve(number, idx + 1, child, sb, results);
                    sb.deleteCharAt(sb.length() - 1);
                }
            }

        }
    }

    public void solveWithSet(String number , int idx, Set<String> wordDict, StringBuilder sb, List<String> results) {

        if (!sb.isEmpty() && wordDict.contains(sb.toString())) {
            results.add(sb.toString());
        }

        if(idx >= number.length())
            return;

        String mappedLetter = lettersMap.get(number.charAt(idx));

        if (mappedLetter != null) {
            for (char c : mappedLetter.toCharArray()) {

                    sb.append(c);
                    solveWithSet(number, idx + 1, wordDict, sb, results);
                    sb.deleteCharAt(sb.length() - 1);
                }


        }
    }

    public void addWordsToTrie(String[] dictionary) {
        for (String word : dictionary) {
            addWord(word);
        }
    }

    public List<String> getData(String number , String[] dict){

        List<String> results = new ArrayList<>();

        solve(number, 0, root,new StringBuilder(),results);

        return results;
    }

    public List<String> getDataWithSet(String number , String[] dict){

        List<String> results = new ArrayList<>();

        solveWithSet(number, 0, new HashSet<>(List.of(dict)),new StringBuilder(),results);

        return results;
    }



    public class Trie{

        Trie[] children;
        boolean isWord;

        public Trie(){
            this.children = new Trie[26];
            isWord = false;
        }

    }


    public static void main(String[] args){

        T9Dictionary t9  = new T9Dictionary();


        String[] dictionary = new String[]{"hello", "good", "gone", "home", "gel", "hoe"};

        t9.addWordsToTrie(dictionary);

        List<String> results = t9.getData("43556",dictionary);
        List<String> resultsWithSet = t9.getDataWithSet("43556",dictionary);

        System.out.println(results);
        System.out.println(resultsWithSet);


    }
}
