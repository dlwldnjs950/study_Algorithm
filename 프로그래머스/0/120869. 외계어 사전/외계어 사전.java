import java.util.*;
class Solution {
    public int solution(String[] spell, String[] dic) {
        int answer = 0;
        for(String d : dic){
            // spell의 길이보다 짧으면 다 못씀
            if(d.length() < spell.length)
                continue;
            String remove = String.valueOf(d);
            for(String s : spell){
                remove = remove.replaceFirst(s,"");
            }
            //System.out.println(remove);
            if(remove.length() == 0)
                return 1;
        }
        return 2;
    }
}