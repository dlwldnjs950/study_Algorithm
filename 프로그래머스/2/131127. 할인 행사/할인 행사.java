import java.util.*;
/* 
    want와 number로 Map 하나 만들고
    맨 앞부터 10까지 초기 Map 구성하고 슬라이딩 윈도우해서
    개수 만족 하는지 확인은 어케할까
    want 기준 for문
*/
class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        
        Map<String, Integer> wants = new HashMap<>();
        for(int idx = 0; idx < want.length; idx++){
            wants.put(want[idx], number[idx]);
        }
        
        // 슬라이딩 윈도우를 위한 표시
        Map<String, Integer> marks = new HashMap<>();
        // 10일간 회원
        for(int idx = 0; idx < 10; idx++){
            int cnt = marks.getOrDefault(discount[idx], 0);
            marks.put(discount[idx], cnt + 1);
        }
        if(isOk(wants, marks))
            answer++;
        
        // 슬라이딩 윈도우
        for(int remove = 0; remove < discount.length; remove++){
            
            // 제거
            String removeGoods = discount[remove];
            marks.put(removeGoods, marks.get(removeGoods) - 1);
            
            // 추가
            // add 인덱스가 기간 내일 경우에만
            int add = remove + 10;
            if(add < discount.length){
                String addGoods = discount[add];
                marks.put(addGoods, marks.getOrDefault(addGoods, 0) + 1);
            }
            
            if(isOk(wants, marks))
            answer++;
        }
        
        
        return answer;
    }
    
    boolean isOk(Map<String, Integer> wants, Map<String, Integer> marks){
        
        for(String want : wants.keySet()){
            int target = wants.get(want);   // 목표 수량
            int mark = marks.getOrDefault(want, 0); // 가능 수량
            if(target > mark)
                return false;
        }
        
        return true;
    }
}