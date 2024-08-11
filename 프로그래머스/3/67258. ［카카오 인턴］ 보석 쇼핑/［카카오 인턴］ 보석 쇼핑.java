import java.util.*;
// 길이마다 확인하면 시간 복잡도가 너무 크다...
class Solution {
    public int[] solution(String[] gems) {
        
        Set<String> gemKinds = new HashSet<>(Arrays.asList(gems));
        Map<String, Integer> gemCnts = new HashMap<>();
        
        int[] answer = new int[2];
        
        int start = 0; int end = 0;
        int minLength = Integer.MAX_VALUE;
        while(end < gems.length){
            gemCnts.put(gems[end], gemCnts.getOrDefault(gems[end], 0) +1);
            end++;
            
            // 모든 종류의 보석을 포함하고 있을 때, 윈도우를 줄이며 최적 구간을 찾음
            while(gemCnts.size() == gemKinds.size()){
                if (end - start < minLength) {
                    minLength = end - start;
                    answer[0] = start + 1;
                    answer[1] = end;
                }
                
                // 시작점 보석 제거 (윈도우 축소)
                gemCnts.put(gems[start], gemCnts.get(gems[start]) - 1);
                if (gemCnts.get(gems[start]) == 0) {
                    gemCnts.remove(gems[start]);
                }
                start++;
            }
        }
        
        return answer;
    }
}