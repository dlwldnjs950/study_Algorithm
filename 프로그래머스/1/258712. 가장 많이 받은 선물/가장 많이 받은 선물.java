import java.util.*;
import java.io.*;

/*
이번달 선물 주고받은 기준 -> 다음달 누가 선물을 많이 받나
선물을 주고받은 두 사람 사이에 더 많은 선물을 준 사람이 다음달에 선물 하나
                        기록 없거나 같으면 선물 지수가 더 큰 사람이 선물 지수가 작은 사람에게 받음
        선물지수 : 이번달까지 자신이 친구들에게 준 선물의 수 - 받은 선물 수
                        선물 지수까지 같으면 주고받지 않음
    받을 선물의 수!
각 친구마다 서로 비교, 선물지수
서로 주고 받은거 표시할 2차원 배열
각자의 선물 지수
*/

class Solution {
    public int solution(String[] friends, String[] gifts) {
        
        int fNum = friends.length;
        
        // 각 친구의 번호 부여하기
        Map<String, Integer> fIdx = new HashMap<>();
        for(int idx=0; idx<fNum; idx++){
            fIdx.put(friends[idx], idx);
        }
        
        // 주고받은 개수
        int[][] cntFromTo = new int[fNum][fNum];
        // 선물지수
        int[] giftScore = new int[fNum];
        
        for(String gift: gifts){
            String[] gFromTo = gift.split(" ");
            int from = fIdx.get(gFromTo[0]);
            int to = fIdx.get(gFromTo[1]);
            
            cntFromTo[from][to]++;
            giftScore[from]++;
            giftScore[to]--;
        }
        
        // 다음달 선물 받는 개수
        // 더 많은 선물을 준 사람 -> 없거나같으면 선물지수가 높은 사람 -> 선물지수까지 같으면 선물X
        int[] nextGiftCnt = new int[fNum];
        int answer = Integer.MIN_VALUE;
        for(int from=0; from<fNum; from++){
            for(int to=from+1; to<fNum; to++){
                // if(from==to)
                //     continue;
                if(cntFromTo[from][to] > cntFromTo[to][from])
                    nextGiftCnt[from]++;
                else if(cntFromTo[from][to] < cntFromTo[to][from])
                    nextGiftCnt[to]++;
                else{
                    if(giftScore[from] > giftScore[to])
                        nextGiftCnt[from]++;
                    else if(giftScore[from] < giftScore[to])
                        nextGiftCnt[to]++;
                }
                answer = Math.max(answer, Math.max(nextGiftCnt[from],nextGiftCnt[to]));
            }
        }
        
        
        return answer;
    }
}