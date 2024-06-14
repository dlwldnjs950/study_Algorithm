import java.util.*;

/*
5자리까지만 단어만들기
*/
class Solution {
    
    public static final char[] alpha = {'A', 'E', 'I', 'O', 'U'};
    public List<String> wordList = new ArrayList<>();
    public int solution(String word) {
        
        makeWord("", word);
        
        // 빈 문자열도 리스트에 하나 있기 때문에 -1...
        return wordList.size() -1;
    }
    
    public void makeWord(String midWord, String target){
        
        // 최대 단어 길이는 UUUUU
        if(midWord.length()>5)
            return;
        
        // 단어 목록에 이미 있다면 그만 확인
        if(wordList.contains(target))
            return;
        
        // 목록에 추가
        wordList.add(midWord);
        // System.out.println(midWord);
        
        // 각 자리수마다 하나씩 붙여가면서 확인
        for(int idx=0; idx<alpha.length; idx++){
            makeWord(midWord + alpha[idx], target);
        }
        
    }
}