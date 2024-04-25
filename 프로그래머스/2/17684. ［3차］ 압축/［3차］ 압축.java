import java.util.*;
/*
무손실 압축 알고리즘
1. 길이 1인 모든 단어를 포함하도록 사전 초기화 (A~Z 추가)
2. 현재 입력과 일치하는 가장 긴 문자열 w
3. w에 해당하는 사전 색인 번호 출력, 입력에서 w 제거
4. 입력에서 처리되지 않은 다음 글자가 남았다면 w+c에 해당하는 단어 사전에 등록
*/
class Solution {
    public List<Integer> solution(String msg) {
        // 색인 (1단계)
        Map<String, Integer> indexes = new HashMap<>();
        int newIdx = 1;
        for(char c='A'; c<='Z'; c++){
            indexes.put(String.valueOf(c), newIdx++);
        }
        
        List<Integer> result = new ArrayList<>();
        
        for(int startIdx = 0; startIdx<msg.length(); startIdx++){
            // 확인할 문자열
            StringBuilder w = new StringBuilder(String.valueOf(msg.charAt(startIdx)));
            
            // 마지막 문자라면 문자(열)의 색인값을 저장하고 끝냄
            if(startIdx == msg.length() - 1){
                result.add(indexes.get(w.toString()));
                break;
            }
            
            // 다음 문자
            String c = String.valueOf(msg.charAt(startIdx+1));
            
            // 다음으로 확인할 문자열이 이미 색인이 지정되어있는 동안 문자열을 늘려나간다
            while(indexes.containsKey(w+c)){
                w.append(c);
                startIdx++;
                
                if(startIdx == msg.length()-1 || c.equals("")){
                    c="";
                    break;
                }
                c = String.valueOf(msg.charAt(startIdx+1));
            }
            
            // 사전에 추가
            if(!(indexes.containsKey(w+c)))
            indexes.put(w+c, newIdx++);
            
            // 색인 출력
            System.out.println(w);
            result.add(indexes.get(w.toString()));
            
        }
        
        int[] answer = {};
        
        return result;
    }
}