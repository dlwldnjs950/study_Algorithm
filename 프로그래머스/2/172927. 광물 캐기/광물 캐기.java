import java.util.*;
/*
곡괭이 : 다이아몬드 0, 철 1, 돌 2 (개수 : 0~5개)
        피로도
        5번 사용하면 더이상 사용할 수 없음

*/
class Solution {
    // row : 곡괭이, col : 광물
    // 다이아몬드 0, 철 1, 돌 2
    final int[][] tiredPoint = {{1,1,1}, {5,1,1}, {25,5,1}};
    int answer = Integer.MAX_VALUE;
    
    public int solution(int[] picks, String[] minerals) {
        int totalPickCnt = picks[0] + picks[1] + picks[2];
        work(0, 0, totalPickCnt, picks, minerals);
        return answer;
    }
    
    // 현재 캘 광물, 현재 곡괭이 번호, 현재 곡괭이 사용 횟수, 현재까지 소모 피로도
    void work(int mineCnt, int tired, int pickCnt, int[] picks, String[] minerals){
        
        // 광물을 다 캤거나 곡괭이 다 썼으면
        if(mineCnt == minerals.length || pickCnt == 0){
            answer = Math.min(tired, answer);
            return;
        }
        
        // 어떤 곡괭이를 쓸까
        for(int idx = 0; idx<3; idx++){
            
            if(picks[idx] == 0) continue;
            
            // 선택한 곡괭이로 생긴 피로도
            int tmpTired = 0;
            
            // 5개 묶어서 피로도 조사
            // 5묶음 또는 광물 마지막까지
            int limit = Math.min(mineCnt+5, minerals.length);
            for(int mIdx = mineCnt; mIdx < limit; mIdx++){
                String nowMine = minerals[mIdx];
                
                int nowMineIdx = nowMine.equals("diamond") ? 0 : nowMine.equals("iron") ? 1: 2;
                
                tmpTired += tiredPoint[idx][nowMineIdx];
            }
            
            picks[idx]--;
            work(limit, tired + tmpTired, pickCnt - 1, picks, minerals);
            picks[idx]++;
        }
    }
}