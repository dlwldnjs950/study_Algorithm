import java.util.*;
class Solution {
    public int solution(int[] stones, int k) {
        int left = 1;   // 최소 인원
        int right = 0;  // 최대 인원
        
        // 가능한 인원 수를 이분법으로 확인하며 범위를 좁혀나간다
        
        for(int stone : stones){
            right = Math.max(right, stone);
        }
        
        while(left <= right){
            int mid = (left + right) / 2;
            
            // 건널 수 있다면 인원을 최소치를 늘려본다
            if(canCross(mid, stones, k)){
                left = mid + 1;
            }else{
            // 건널 수 없다면 인원 최대치를 줄여본다
                right = mid -1;
            }
        }
        
        return right;
    }
    
    boolean canCross(int mid, int[] stones, int k){
        int cnt = 0;
        
        // 각 비용에서 인원수를 빼보고 건너뛰는 돌의 수 카운트
        for(int stone : stones){
            if(stone - mid < 0)
                cnt++;
            else
                cnt = 0;
            
            // 넘어서는 구간이 있다면 return false
            if(cnt >= k)
                return false;
        }
        return true;
    }
}