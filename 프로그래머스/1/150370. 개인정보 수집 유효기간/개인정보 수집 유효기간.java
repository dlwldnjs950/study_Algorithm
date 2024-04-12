import java.util.*;
import java.io.*;

/*
1~n번의 개인정보
각 약관마다 개인정보 보관 유효기간
유효기간 / 수집된날 => 오늘 날짜로 파기해야할 개인정보 번호 구하기
모든 달은 28일까지
*/

class Solution {
    public Integer[] solution(String today, String[] terms, String[] privacies) {
        // 오늘 날짜
        int[] todays = new int[3];
        int idx=0;
        for(String tmp:today.split("\\.")){
            todays[idx++]=Integer.parseInt(tmp);
        }
        int tDate = todays[0]*12*28 + todays[1]*28 + todays[2];
        
        // terms : 약관종류 유효기간 (공백 구분) -> Map
        Map<String, Integer> termsMap = new HashMap<>();
        for(String term: terms){
            String[] tmp = term.split(" ");
            termsMap.put(tmp[0], Integer.parseInt(tmp[1]));
        }
        
        // 제거 개인정보 List
        List<Integer> answerList = new ArrayList<>();
        // privacies : 날짜 약관종류 (공백구분)
        int num=1;
        for(String privacy: privacies){
            String[] tmp = privacy.split(" ");
            
            // 수집 날짜 분리
            String[] str = tmp[0].split("\\.");
            int[] dates = new int[3];
            idx=0;
            for(String t:str){
                dates[idx++]=Integer.parseInt(t);
            }
            
            // 약관종류에 따른 유효 기간
            int limit = termsMap.get(tmp[1]);
            
            // 만료일
            int pDate = dates[0]*12*28 + dates[1]*28 + dates[2] + limit*28;
            
            // 유효성 검사
            if(pDate <= tDate){
                answerList.add(num);
            }
            num++;
        }
        
        
        Integer[] answer = answerList.toArray(new Integer[answerList.size()]);
        return answer;
    }
}