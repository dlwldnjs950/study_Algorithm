import java.util.*;
class Solution {
    public int solution(String name) {
        int length = name.length();
        
        int numberMove = 0;
        int directionMove = 0;

        int minMove = length - 1; // 좌우 이동 횟수의 최솟값, 일단 오른쪽으로 쭉 가는 경우로 초기화
        
        for(int idx=0; idx< length; idx++){
            int diff = name.charAt(idx) - 'A';
            numberMove += Math.min(diff, 26-diff);
            
            // 다음 위치에서 연속된 'A'의 개수 찾기
            int nextIdx = idx + 1;
            while (nextIdx < length && name.charAt(nextIdx) == 'A') {
                nextIdx++;
            }
            
            // 오른쪽으로 갔다가 왼쪽으로 돌아오는 경우와
            // 왼쪽으로 갔다가 오른쪽으로 돌아오는 경우 중 최소를 선택
            minMove = Math.min(minMove, idx + length - nextIdx + Math.min(idx, length - nextIdx));
        }
                
        return numberMove + minMove;
    }
}