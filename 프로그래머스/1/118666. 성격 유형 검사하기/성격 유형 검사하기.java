import java.util.*;
/*
4가지의 지표, 각 지표에 2가지 유형
지표별 유형 관리
각 유형별 점수 관리

매우 : 3점
    : 2점
약간 : 1점

각 지표에서 점수가 더 높은 유형
점수가 같으면 사전순으로 빠른 유형

survey의 각 원소는 2글자 : 첫번째 글자는 비동의, 두번째 글자는 동의
choices 1~3 : 비동의, 4 : 모름, 5~7 : 동의
비동의 =  4-choices, 0, 동의 = choices-4
*/
class Solution {
    public String solution(String[] survey, int[] choices) {
        
        char[][] types = new char[][]{{'R','T'}, {'C','F'}, {'J','M'}, {'A','N'}};
        
        Map<Character, Integer> scores = new HashMap<>();
        for(char[] type : types){
            scores.put(type[0], 0);
            scores.put(type[1], 0);
        }
        
        // 각 유형 점수 계산
        // 각 선택에 대한 질문지를 확인
        for(int idx=0; idx<choices.length; idx++){
            int choice = choices[idx];
            String sur = survey[idx];
            
            // 비동의
            if(choice>=1 && choice<=3){
              scores.put(sur.charAt(0), scores.get(sur.charAt(0))+(4-choice));
            // 동의
            }else if(choice>=5 && choice<=7){
              scores.put(sur.charAt(1), scores.get(sur.charAt(1))-(4-choice));
            }
        }
        
        String answer = "";
        // 성격유형 조합하기
        for(char[] type : types){
            int score0= scores.get(type[0]);
            int score1= scores.get(type[1]);
            if(score0 > score1){
                answer += type[0];
            }else if(score0 < score1){
                answer += type[1];
            }else{
                answer += (type[0] < type[1] ? type[0] : type[1]);
            }
        }
        return answer;
    }
}